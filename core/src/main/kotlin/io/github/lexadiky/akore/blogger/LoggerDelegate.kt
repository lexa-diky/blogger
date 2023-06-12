package io.github.lexadiky.akore.blogger

typealias LoggerDelegateParametersConsumer<T> = (level: LogLevel, tag: String?, message: String, throwable: Throwable?) -> T

fun interface LoggerDelegate {

    fun log(level: LogLevel, tag: String?, message: String, throwable: Throwable?)
}
