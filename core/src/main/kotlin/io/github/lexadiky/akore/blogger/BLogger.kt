package io.github.lexadiky.akore.blogger

import io.github.lexadiky.akore.blogger.impl.NoOpLoggerDelegate
import io.github.lexadiky.akore.blogger.impl.StaticContextualLogger

object BLogger : LoggerDelegate, ContextualLoggerDelegate.Factory {

    private var internalDelegate: LoggerDelegate? = null
    private val delegate: LoggerDelegate
        get() = internalDelegate ?: NoOpLoggerDelegate

    override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) {
        delegate.log(level, tag, message, throwable)
    }

    override fun tag(tag: String): ContextualLoggerDelegate = StaticContextualLogger.apply {
        contextTag.set(tag)
    }

    fun configure(override: Boolean = false, conf: LoggerConfigurator.() -> Unit) {
        val configurator = LoggerConfigurator()
        configurator.conf()
        install(override, configurator.build())
    }

    private fun install(override: Boolean, delegate: LoggerDelegate) {
        if (internalDelegate == null || override) {
            internalDelegate = delegate
        } else {
            error("logger delegate already initialized")
        }
    }

}
