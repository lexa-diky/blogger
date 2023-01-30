package io.github.lexadiky.akore.blogger

import org.junit.jupiter.api.Test

class Sample {

    @Test
    fun setup() {
        val printlnLogger = LoggerDelegate { level, tag, message, throwable ->
            println(message)
        }
        BLogger.configure {
            source pipeTo printlnLogger where { level, _, _, _ ->
                level != LoggerLevel.ERROR
            }
        }

        BLogger.tagged()
            .info("Hello World")

        BLogger.tagged()
            .error("Error")
    }
}