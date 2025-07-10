package blackjack

import blackjack.model.HandCards
import blackjack.model.Player
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["1234", "", "abcdefghijklmnopqrstxyz"])
    fun `test failing names`(name: String) {
        assertThrows<IllegalArgumentException> { Player(name, HandCards()) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["abcdef", "blackjacker", "pobi"])
    fun `test good names`(name: String) {
        assertDoesNotThrow { Player(name, HandCards()) }
    }
}
