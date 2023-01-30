package io.github.lexadiky.akore.blogger

import io.github.lexadiky.akore.blogger.impl.FanOutLogger

class LoggerConfigurator {
    private val fanOutLogger = FanOutLogger()

    val source: LogsProducerMarker = LogsProducerMarker

    private var currentBuildingLoggerDelegate: LoggerDelegate? = null

    infix fun LogsProducerMarker.pipeTo(to: LoggerDelegate): LoggerMapping {
        installCurrentDelegate()
        currentBuildingLoggerDelegate = to
        return LoggerMapping
    }

    infix fun LoggerMapping.where(condition: LoggerDelegateParametersConsumer<Boolean>) {
        val wrapLogger = currentBuildingLoggerDelegate
        currentBuildingLoggerDelegate = LoggerDelegate { level, tag, message, throwable ->
            if (condition(level, tag, message, throwable)) {
                wrapLogger?.log(level, tag, message, throwable)
            }
        }
    }

    internal fun build(): LoggerDelegate {
        installCurrentDelegate()

        return fanOutLogger
    }

    private fun installCurrentDelegate() {
        if (currentBuildingLoggerDelegate != null) {
            fanOutLogger.install(currentBuildingLoggerDelegate!!)
            currentBuildingLoggerDelegate = null
        }
    }

    object LogsProducerMarker

    object LoggerMapping
}