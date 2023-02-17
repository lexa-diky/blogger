package io.github.lexadiky.akore.blogger

import io.github.lexadiky.akore.blogger.harness.EqAssertionValue
import org.junit.jupiter.api.Assertions

class TestLoggerDelegate : LoggerDelegate {

    private val records: ArrayList<Record> = ArrayList()

    override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) {
        this.records += Record(level, tag, message, throwable)
    }

    fun assert(asserter: LoggerDelegateAssertionContext.() -> Unit) {
        LoggerDelegateAssertionContext(records).apply(asserter)
    }

    data class Record(
        val level: LoggerLevel,
        val tag: String?,
        val message: String,
        val throwable: Throwable?
    )

    companion object {

        fun install(blogger: BLogger): TestLoggerDelegate {
            val self = TestLoggerDelegate()
            blogger.configure(override = true) {
                source pipeTo self
            }
            return self
        }
    }
}

class LoggerDelegateAssertionContext(private val record: List<TestLoggerDelegate.Record>) {
    private val stack = record.toMutableList()

    fun message(check: MessageAssertionContext.() -> Unit) {
        Assertions.assertTrue(stack.isNotEmpty(), "should have recorded more messages")
        val next = stack.first()
        stack.removeAt(0)
        MessageAssertionContext(next).apply(check)
    }

    class MessageAssertionContext(record: TestLoggerDelegate.Record) {

        val level = EqAssertionValue(record.level)
        val message = EqAssertionValue(record.message)
        val tag = EqAssertionValue(record.tag)
        val throwable = EqAssertionValue(record.throwable)
    }
}