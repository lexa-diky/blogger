package io.github.lexadiky.akore.blogger.impl

import io.github.lexadiky.akore.blogger.LoggerDelegate
import io.github.lexadiky.akore.blogger.LogLevel

internal object NoOpLoggerDelegate : LoggerDelegate {

    override fun log(level: LogLevel, tag: String?, message: String, throwable: Throwable?) = Unit
}