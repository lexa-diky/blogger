package io.github.lexadiky.akore.blogger

import io.github.lexadiky.akore.blogger.harness.TestLoggerDelegate
import org.junit.jupiter.api.Test

class BloggerTest {

    @Test
    fun `WHEN call tagged log THEN record message`() {
        val tester = TestLoggerDelegate.install(BLogger)

        BLogger.tagged()
            .info("some message")

        tester.assert {
            message {
                level eq LogLevel.INFO
                message eq "some message"
                tag eq "io.github.lexadiky.akore.blogger.BloggerTest"
            }
        }
    }

    @Test
    fun `WHEN fail assertion with no throw setting THEN do not throw`() {
        BLogger.configure {
            throwOnFailedAssertion = false
            source pipeTo LoggerDelegate { _, message, _, _ -> println(message) }
        }

        BLogger.tag("hello")
            .assert("should not fail", false)

        @Suppress("SimplifyBooleanWithConstants")
        assert(true == true)
    }
}