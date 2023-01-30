package io.github.lexadiky.akore.blogger.impl

import io.github.lexadiky.akore.blogger.LoggerDelegate
import io.github.lexadiky.akore.blogger.LoggerLevel

internal object NoOpLoggerDelegate : LoggerDelegate {

    override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) = Unit
}