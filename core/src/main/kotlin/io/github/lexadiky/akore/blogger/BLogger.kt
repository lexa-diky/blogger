package io.github.lexadiky.akore.blogger

import io.github.lexadiky.akore.blogger.impl.StaticContextualLogger

object BLogger : LoggerDelegate, ContextualLoggerDelegate.Factory {

    private var internalDelegate: LoggerDelegate? = null
    private val delegate: LoggerDelegate
        get() = internalDelegate ?: kotlin.error("no logger delegate initialized")

    override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) {
        delegate.log(level, tag, message, throwable)
    }

    override fun tag(tag: String) = StaticContextualLogger.apply {
        contextTag.set(tag)
    } as ContextualLoggerDelegate

    fun install(delegate: LoggerDelegate) {
        if (internalDelegate == null) {
            internalDelegate = delegate
        } else {
            error("logger delegate already initialized")
        }
    }
}