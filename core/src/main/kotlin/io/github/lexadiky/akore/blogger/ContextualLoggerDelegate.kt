package io.github.lexadiky.akore.blogger

interface ContextualLoggerDelegate : LoggerDelegate {

    interface Factory {

        fun tag(tag: String): ContextualLoggerDelegate
    }
}
