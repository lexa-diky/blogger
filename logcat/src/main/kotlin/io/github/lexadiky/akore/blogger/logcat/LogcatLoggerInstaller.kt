package io.github.lexadiky.akore.blogger.logcat

import io.github.lexadiky.akore.blogger.LoggerConfigurator
import io.github.lexadiky.akore.blogger.LoggerDelegate

val LoggerConfigurator.logcat: LoggerDelegate get() = LogcatLoggerDelegate()
