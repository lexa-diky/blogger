package io.github.lexadiky.akore.blogger

import io.github.lexadiky.akore.blogger.harness.TestLoggerDelegate
import org.junit.jupiter.api.Test

class BloggerTest {

    @Test
    fun `WHEN call tagged log THEN record message`() {
        val tester = TestLoggerDelegate.install(BLogger)

        BLogger.tagged()
            .info("some message")

        tester.assert {
            message {
                level eq LoggerLevel.INFO
                message eq "some message"
                tag eq "io.github.lexadiky.akore.blogger.BloggerTest"
            }
        }
    }
}