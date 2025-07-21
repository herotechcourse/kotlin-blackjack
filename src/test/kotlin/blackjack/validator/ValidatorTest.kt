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

    @ValueSource(ints = [1000, 11000, 21000, 50000])
    @ParameterizedTest
    fun `does not throw with correct amount`(amount: Int) {
        assertDoesNotThrow {
            Validator.amount(amount)
        }
    }

    @ValueSource(ints = [-10, 0, 100, 10001, 21111])
    @ParameterizedTest
    fun `throw with incorrect amount`(amount: Int) {
        assertThrows<IllegalArgumentException> {
            Validator.amount(amount)
        }
    }
}
