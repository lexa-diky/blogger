package io.github.lexadiky.akore.blogger.impl

import io.github.lexadiky.akore.blogger.LoggerDelegate
import io.github.lexadiky.akore.blogger.LogLevel

internal class FanOutLogger : LoggerDelegate {

    private val subLoggers: MutableList<LoggerDelegate> = ArrayList()

    override fun log(level: LogLevel, tag: String?, message: String, throwable: Throwable?) {
        subLoggers.forEach { logger ->
            logger.log(level, tag, message, throwable)
        }
    }

    fun install(subLogger: LoggerDelegate) {
        subLoggers += subLogger
    }
}