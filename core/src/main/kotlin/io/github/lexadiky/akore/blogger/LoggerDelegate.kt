package io.github.lexadiky.akore.blogger

interface LoggerDelegate {

    fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?)
}
