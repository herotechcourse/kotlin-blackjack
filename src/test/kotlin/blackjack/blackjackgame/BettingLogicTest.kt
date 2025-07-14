package blackjack.blackjackgame

import blackjack.model.GameResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BettingLogicTest {
    @Test
    fun `should calculate earnings for regular win`() {
        val result = GameResult.WIN
        val earnings = BettingLogic.calculateEarnings(result, isBlackjack = false, bet = 10000)
        assertEquals(10000, earnings)
    }

    @Test
    fun `should calculate earnings for blackjack win`() {
        val result = GameResult.WIN
        val earnings = BettingLogic.calculateEarnings(result, isBlackjack = true, bet = 10000)
        assertEquals(15000, earnings)
    }

    @Test
    fun `should calculate earnings for loss`() {
        val result = GameResult.LOSE
        val earnings = BettingLogic.calculateEarnings(result, isBlackjack = false, bet = 10000)
        assertEquals(-10000, earnings)
    }

    @Test
    fun `should calculate earnings for draw`() {
        val result = GameResult.DRAW
        val earnings = BettingLogic.calculateEarnings(result, isBlackjack = false, bet = 10000)
        assertEquals(0, earnings)
    }
}
