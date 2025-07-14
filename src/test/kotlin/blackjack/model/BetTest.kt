package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BetTest {
    @Test
    fun `player is created with a name and bet`() {
        val bet = Bet(10000)
        val player = Player("player", bet)

        assertEquals("player", player.name)
        assertEquals(10000, player.bet.amount)
    }

    @Test
    fun `bet should be positive`() {
        assertThrows<IllegalArgumentException> {
            Bet(-100)
        }
    }

    @Test
    fun `bet should be more than 0`() {
        assertThrows<IllegalArgumentException> {
            Bet(0)
        }
    }
}
