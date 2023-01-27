package io.github.lexadiky.akore.blogger

object BLogger : ContextualLoggerDelegate {

    private var internalDelegate: LoggerDelegate? = null
    private val delegate: LoggerDelegate
        get() = internalDelegate ?: kotlin.error("no logger delegate initialized")

    override fun log(level: LoggerLevel, tag: String?, message: String, throwable: Throwable?) {
        delegate.log(level, tag, message, throwable)
    }

    override fun tag(tag: String) = apply {
        delegate.tag(tag)
    }

    fun install(delegate: LoggerDelegate) {
        if (internalDelegate == null) {
            internalDelegate = delegate
        } else {
            error("logger delegate already initialized")
        }
    }
}