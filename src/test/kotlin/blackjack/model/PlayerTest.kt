package blackjack.model

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
        assertThrows<IllegalArgumentException> {Player("")}
    }

    @Test
    fun `should return true isBlackjack() if score is 21`() {
        val player = Player("bojana")
        player.score = 21
        assertThat(player.isBlackjack()).isTrue()
    }

    @Test
    fun `should return true isBusts() if score over 21`() {
        val player = Player("bojana")
        player.score = 22
        assertThat(player.isBusts()).isTrue()
    }

    @Test
    fun `should return false isBlackjack() if score is not 21`() {
        val player = Player("bojana")
        player.score = 22
        assertThat(player.isBlackjack()).isFalse()
    }

    @Test
    fun `should return false isBusts() if score not over 21`() {
        val player = Player("bojana")
        player.score = 21
        assertThat(player.isBusts()).isFalse()
    }
}