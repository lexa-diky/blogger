package io.github.lexadiky.akore.blogger.slf4j

import io.github.lexadiky.akore.blogger.LogLevel
import io.github.lexadiky.akore.blogger.LoggerConfigurator
import io.github.lexadiky.akore.blogger.LoggerDelegate
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

/**
 * Implementation of [LoggerDelegate] delegating actual logging into slf4j's logger.
 */
class Slf4JLoggerDelegate : LoggerDelegate {

    override fun log(level: LogLevel, tag: String?, message: String, throwable: Throwable?) {
        val loggingEventBuilder = LoggerFactory.getLogger(tag)
            .atLevel(level.asSlf4J())

        if (throwable != null) {
            loggingEventBuilder.setCause(throwable)
                .log(message)
        } else {
            loggingEventBuilder
                .log(message)
        }
    }

    private fun LogLevel.asSlf4J(): Level = when (this) {
        LogLevel.VERBOSE -> Level.TRACE
        LogLevel.DEBUG -> Level.DEBUG
        LogLevel.INFO -> Level.INFO
        LogLevel.WARNING -> Level.WARN
        LogLevel.ERROR -> Level.ERROR
        LogLevel.ASSERT -> Level.ERROR
    }
}

val LoggerConfigurator.slf4j: LoggerDelegate get() = Slf4JLoggerDelegate()
