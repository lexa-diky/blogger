package io.github.lexadiky.akore.blogger

import io.github.lexadiky.akore.blogger.impl.FanOutLogger

class LoggerConfigurator {
    private val fanOutLogger = FanOutLogger()

    val source: LogsProducerMarker = LogsProducerMarker
    var throwOnFailedAssertion: Boolean = true

    private var latestBuildingLoggerDelegate: LoggerDelegate? = null

    infix fun LogsProducerMarker.pipeTo(to: LoggerDelegate): LoggerMapping {
        installLatestDelegate()
        latestBuildingLoggerDelegate = to
        return LoggerMapping
    }

    infix fun LoggerMapping.where(condition: LoggerDelegateParametersConsumer<Boolean>) {
        val wrapLogger = latestBuildingLoggerDelegate
        latestBuildingLoggerDelegate = LoggerDelegate { level, tag, message, throwable ->
            if (condition(level, tag, message, throwable)) {
                wrapLogger?.log(level, tag, message, throwable)
            }
        }
    }

    internal fun build(): LoggerDelegate {
        installLatestDelegate()

        return fanOutLogger
    }

    private fun installLatestDelegate() {
        if (latestBuildingLoggerDelegate != null) {
            fanOutLogger.install(latestBuildingLoggerDelegate!!)
            latestBuildingLoggerDelegate = null
        }
    }

    private fun installParameters() {
        BLogger.throwOnFailedAssertion = this.throwOnFailedAssertion
    }

    object LogsProducerMarker

    object LoggerMapping
}