package io.github.lexadiky.akore.blogger.logcat

import io.github.lexadiky.akore.blogger.BLogger
import io.github.lexadiky.akore.blogger.LogLevel
import io.github.lexadiky.akore.blogger.info
import org.junit.jupiter.api.Test

class Sample {

    @Test
    fun installation() {
        BLogger.configure {
            source pipeTo logcat where { level, _, _, _ ->
                level != LogLevel.ERROR
            }
        }
        BLogger.tag("TAG")
            .info("hello world")
    }
}