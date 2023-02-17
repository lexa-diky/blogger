package io.github.lexadiky.akore.blogger.harness

import org.junit.jupiter.api.Assertions

class EqAssertionValue<T>(private val value: T) {

    infix fun eq(other: T) {
        Assertions.assertEquals(other, value)
    }
}