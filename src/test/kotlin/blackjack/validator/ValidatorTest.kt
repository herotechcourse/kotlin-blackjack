package blackjack.validator

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ValidatorTest {
    @ValueSource(strings = ["mina", "guri", "ella"])
    @ParameterizedTest
    fun `does not throw with correct name`(name: String) {
        assertDoesNotThrow {
            Validator.name(name)
        }
    }

    @ValueSource(strings = ["m$*na", "guuuuuuuuuuuuuuuri", "el   !!la"])
    @ParameterizedTest
    fun `throw with incorrect name`(name: String) {
        assertThrows<IllegalArgumentException> {
            Validator.name(name)
        }
    }
}
