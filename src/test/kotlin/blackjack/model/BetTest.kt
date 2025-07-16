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

    @Test
    fun `earningsAgainstDealer returns bet on WIN`() {
        val player = Player("player", Bet(10000))
        val result = EarningsResult.WIN_BET
        assertEquals(10000, player.earnings(result))
    }

    @Test
    fun `earningsAgainstDealer returns 0 on TIE`() {
        val player = Player("player", Bet(10000))
        val result = EarningsResult.TIE_BET
        assertEquals(0, player.earnings(result))
    }

    @Test
    fun `earningsAgainstDealer returns -bet on LOSE`() {
        val player = Player("player", Bet(10000))
        val result = EarningsResult.LOSE_BET
        assertEquals(-10000, player.earnings(result))
    }

    @Test
    fun `earningsAgainstDealer returns 1_5x bet on BLACKJACK`() {
        val player = Player("player", Bet(10000))
        val result = EarningsResult.WIN_BLACK_JACK_BET
        assertEquals(15000, player.earnings(result))
    }
}
