package blackjack

import blackjack.model.HandCards
import blackjack.model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["1234", "", "abcdefghijklmnopqrstxyz"])
    fun `test failing names`(name: String) {
        assertThrows<IllegalArgumentException> { Player(name, HandCards(), 1000) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["abcdef", "blackjacker", "pobi"])
    fun `test good names`(name: String) {
        assertDoesNotThrow { Player(name, HandCards(), 1000) }
    }

    @Test
    fun `test player creation with valid et`() {
        val player = Player("pobi", HandCards(), 1000)
        assertThat(player.bet).isEqualTo(1000)
    }

    @Test
    fun `test player creation with invalid bet`() {
        assertThrows<IllegalArgumentException> { Player("pobi", HandCards(), 10) }
    }
}
