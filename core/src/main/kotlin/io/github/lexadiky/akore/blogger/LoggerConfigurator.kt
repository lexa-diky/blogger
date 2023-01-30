package io.github.lexadiky.akore.blogger

import io.github.lexadiky.akore.blogger.impl.FanOutLogger

class LoggerConfigurator {
    private val fanOutLogger = FanOutLogger()

    val source: LogsProducerMarker = LogsProducerMarker

    infix fun LogsProducerMarker.pipe(to: LoggerDelegate) {
        fanOutLogger.install(to)
    }

    internal fun build(): LoggerDelegate {
        return fanOutLogger
    }

    object LogsProducerMarker
}