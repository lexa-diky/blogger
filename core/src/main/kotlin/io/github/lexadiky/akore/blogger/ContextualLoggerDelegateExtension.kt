@file:OptIn(ExperimentalContracts::class)

package io.github.lexadiky.akore.blogger

import java.lang.IllegalStateException
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun ContextualLoggerDelegate.verbose(message: String) {
    log(LogLevel.VERBOSE, null, message, null)
}

fun ContextualLoggerDelegate.debug(message: String) {
    log(LogLevel.DEBUG, null, message, null)
}

fun ContextualLoggerDelegate.info(message: String) {
    log(LogLevel.INFO, null, message, null)
}

fun ContextualLoggerDelegate.warning(message: String) {
    log(LogLevel.WARNING, null, message, null)
}

fun ContextualLoggerDelegate.error(message: String, throwable: Throwable? = null) {
    log(LogLevel.ERROR, null, message, throwable)
}

fun ContextualLoggerDelegate.assert(message: String, condition: Boolean) {
    contract {
        returns() implies condition
    }
    log(LogLevel.ASSERT, null, message, AssertionError(message))
    if (!condition) {
        throw IllegalStateException(message)
    }
}
