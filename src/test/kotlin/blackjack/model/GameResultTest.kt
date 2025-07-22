package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameResultTest {

    @Test
    fun `should calculate earnings for blackjack win`() {
        val earnings = GameResult.BLACKJACK_WIN.earningsFrom(Bet.from(10000))
        assertEquals(15000, earnings)
    }

    @Test
    fun `should calculate earnings for regular win`() {
        val earnings = GameResult.WIN.earningsFrom(Bet.from(10000))
        assertEquals(10000, earnings)
    }

    @Test
    fun `should calculate earnings for draw`() {
        val earnings = GameResult.DRAW.earningsFrom(Bet.from(10000))
        assertEquals(0, earnings)
    }

    @Test
    fun `should calculate earnings for loss`() {
        val earnings = GameResult.LOSE.earningsFrom(Bet.from(10000))
        assertEquals(-10000, earnings)
    }
}
