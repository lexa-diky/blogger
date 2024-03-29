package io.github.lexadiky.akore.blogger

import io.github.lexadiky.akore.blogger.impl.InlineContextualLogger
import io.github.lexadiky.akore.blogger.impl.NoOpLoggerDelegate

/**
 * BLogger is a singleton object that implements the [LoggerDelegate] and [ContextualLoggerDelegate.Factory] interfaces.
 * It provides a logging mechanism for the application.
 */
object BLogger : LoggerDelegate, ContextualLoggerDelegate.Factory {

    private var internalDelegate: LoggerDelegate? = null
    private val delegate: LoggerDelegate
        get() = internalDelegate ?: NoOpLoggerDelegate

    internal var throwOnFailedAssertion: Boolean = true

    override fun log(level: LogLevel, tag: String?, message: String, throwable: Throwable?) {
        delegate.log(level, tag, message, throwable)
    }

    override fun tag(tag: String): ContextualLoggerDelegate = InlineContextualLogger(tag)

    fun configure(override: Boolean = false, conf: LoggerConfigurator.() -> Unit) {
        val configurator = LoggerConfigurator()
        configurator.conf()
        configurator.installParameters()
        install(override, configurator.build())
    }

    private fun install(override: Boolean, delegate: LoggerDelegate) {
        if (internalDelegate == null || override) {
            internalDelegate = delegate
        } else {
            error("logger delegate already initialized")
        }
    }

    override fun toString(): String {
        return "Blogger($internalDelegate)"
    }

    override fun equals(other: Any?): Boolean {
        return other is BLogger
    }
}
