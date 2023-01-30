package io.github.lexadiky.akore.blogger.logcat

import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.LoggerLevel
import io.github.lexadiky.akore.blogger.info
import io.github.lexadiky.akore.blogger.tagged
import org.junit.jupiter.api.Test

class Sample {

    @Test
    fun installation() {
        BLogger.configure {
            source pipeTo logcat where { level, _, _, _ ->
                level != LoggerLevel.ERROR
            }
        }
        BLogger.tag("TAG")
            .info("hello world")
    }
}