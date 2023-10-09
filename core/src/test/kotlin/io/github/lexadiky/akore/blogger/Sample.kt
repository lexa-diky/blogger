package io.github.lexadiky.akore.blogger

import org.junit.jupiter.api.Test

class Sample {

    @Test
    fun setup() {
        val printlnLogger = LoggerDelegate { level, tag, message, throwable ->
            println(message)
        }
        BLogger.configure(override = true) {
            throwOnFailedAssertion = true
            source pipeTo printlnLogger where { level, _, _, _ ->
                level != LogLevel.ERROR
            }
        }

        BLogger.tagged()
            .info("Hello World")

        BLogger.tagged()
            .error("Error")
    }
}