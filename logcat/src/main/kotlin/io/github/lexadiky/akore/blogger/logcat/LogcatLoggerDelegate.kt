package io.github.lexadiky.akore.blogger.logcat

import android.util.Log
import io.github.lexadiky.akore.blogger.LoggerConfigurator
import io.github.lexadiky.akore.blogger.LoggerDelegate
import io.github.lexadiky.akore.blogger.LoggerLevel

internal class LogcatLoggerDelegate : LoggerDelegate {

    override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) {
        if (throwable != null) {
            Log.println(
                level.asLogcatPriority(),
                tag,
                "$message\n${Log.getStackTraceString(throwable)}",
            )
        } else {
            Log.println(
                level.asLogcatPriority(),
                tag,
                message,
            )
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun LoggerLevel.asLogcatPriority(): Int = when (this) {
        LoggerLevel.VERBOSE -> Log.VERBOSE
        LoggerLevel.DEBUG -> Log.DEBUG
        LoggerLevel.INFO -> Log.INFO
        LoggerLevel.WARNING -> Log.WARN
        LoggerLevel.ERROR -> Log.ERROR
        LoggerLevel.ASSERT -> Log.ASSERT
    }
}

val LoggerConfigurator.logcat: LoggerDelegate get() = LogcatLoggerDelegate()
