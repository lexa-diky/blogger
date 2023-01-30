package io.github.lexadiky.akore.blogger

context(Any)
fun ContextualLoggerDelegate.Factory.tagged(): ContextualLoggerDelegate {
    return tag(this@Any::class.java.name)
}
