package io.github.lexadiky.akore.blogger

typealias LoggerDelegateParametersConsumer<T> = (level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) -> T

fun interface LoggerDelegate {

    fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?)
}
