package blackjack.model

import blackjack.model.card.Card
import blackjack.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    fun `should initialize a Player with a valid name string`() {
        assertThat(Player("bojana")).isInstanceOf(Player::class.java)
    }

    @Test
    fun `should throw an error if player name is empty`() {
        assertThrows<IllegalArgumentException> { Player("") }
    }
}
