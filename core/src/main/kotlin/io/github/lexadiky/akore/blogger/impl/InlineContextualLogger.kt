package io.github.lexadiky.akore.blogger.impl

import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.ContextualLoggerDelegate
import io.github.lexadiky.akore.blogger.LogLevel

@JvmInline
value class InlineContextualLogger(private val tag: String) : ContextualLoggerDelegate {

    override fun log(level: LogLevel, tag: String?, message: String, throwable: Throwable?) {
        BLogger.log(level, tag ?: this.tag, message, throwable)
    }
}
