package io.github.lexadiky.akore.blogger.logcat

import android.util.Log
import io.github.lexadiky.akore.blogger.LoggerConfigurator
import io.github.lexadiky.akore.blogger.LoggerDelegate
import io.github.lexadiky.akore.blogger.LogLevel

/**
 * A delegate for logging messages to the Android Logcat.
 */
internal class LogcatLoggerDelegate : LoggerDelegate {

    override fun log(level: LogLevel, tag: String?, message: String, throwable: Throwable?) {
        val sanitizedTag = sanitizeTag(tag)

        if (throwable != null) {
            Log.println(
                level.asLogcatPriority(),
                sanitizedTag,
                "$message\n${Log.getStackTraceString(throwable)}",
            )
        } else {
            Log.println(
                level.asLogcatPriority(),
                sanitizedTag,
                message,
            )
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun LogLevel.asLogcatPriority(): Int = when (this) {
        LogLevel.VERBOSE -> Log.VERBOSE
        LogLevel.DEBUG -> Log.DEBUG
        LogLevel.INFO -> Log.INFO
        LogLevel.WARNING -> Log.WARN
        LogLevel.ERROR -> Log.ERROR
        LogLevel.ASSERT -> Log.ASSERT
    }

    private fun sanitizeTag(tag: String?): String? {
        if (tag == null || tag.length < MAX_TAG_SIZE) {
            return tag
        }

        val trimmedTag = tag.take(MAX_TAG_SIZE)
        log(
            level = LogLevel.WARNING,
            tag = "BLogger.self",
            message = "tag '$tag' is to long to be handled by logcat, was trimmed to '$trimmedTag', consider using shorter one",
            throwable = null
        )

        return trimmedTag
    }

    companion object {

        private const val MAX_TAG_SIZE = 23
    }
}
