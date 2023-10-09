@file:OptIn(ExperimentalContracts::class)

package io.github.lexadiky.akore.blogger

import kotlin.IllegalStateException
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

fun ContextualLoggerDelegate.panic(message: String, throwable: Throwable? = null): Nothing {
    log(LogLevel.ERROR, null, message, throwable)
    if (throwable != null) {
        throw throwable
    } else {
        throw IllegalStateException(message)
    }
}

fun ContextualLoggerDelegate.assert(
    message: String,
    condition: Boolean,
    throws: Boolean = BLogger.throwOnFailedAssertion
) {
    contract {
        returns() implies (condition && !throws)
    }
    log(LogLevel.ASSERT, null, message, AssertionError(message))
    if (!condition && throws) {

    }
}
