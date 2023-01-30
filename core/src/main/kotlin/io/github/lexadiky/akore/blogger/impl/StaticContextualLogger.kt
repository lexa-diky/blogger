package io.github.lexadiky.akore.blogger.impl

import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.ContextualLoggerDelegate
import io.github.lexadiky.akore.blogger.LoggerLevel

internal object StaticContextualLogger : ContextualLoggerDelegate {

    val contextTag: ThreadLocal<String?> = ThreadLocal()

    override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) {
        BLogger.log(level, tag ?: contextTag.get(), message, throwable)
    }
}
