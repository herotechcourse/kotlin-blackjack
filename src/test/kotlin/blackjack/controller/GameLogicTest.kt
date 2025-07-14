package blackjack.controller

import blackjack.model.GameResult
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class GameLogicTest {

    @Test
    fun `returns WIN when player has higher score and no one is busted`() {
        val result = GameLogic.getGameResult(
            playerScore = 20,
            dealerScore = 18,
            isPlayerBusted = false,
            isDealerBusted = false
        )
        Assertions.assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `returns LOSE when player is busted`() {
        val result = GameLogic.getGameResult(
            playerScore = 22,
            dealerScore = 17,
            isPlayerBusted = true,
            isDealerBusted = false
        )
        Assertions.assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `returns DRAW when scores are equal and no one is busted`() {
        val result = GameLogic.getGameResult(
            playerScore = 20,
            dealerScore = 20,
            isPlayerBusted = false,
            isDealerBusted = false
        )
        Assertions.assertEquals(GameResult.DRAW, result)
    }

    @Test
    fun `should calculate earnings for regular win`() {
        val result = GameResult.WIN
        val earnings = GameLogic.calculateEarnings(result, 20, isBlackjack = false, bet = 10000)
        assertEquals(10000, earnings)
    }

    @Test
    fun `should calculate earnings for blackjack win`() {
        val result = GameResult.WIN
        val earnings = GameLogic.calculateEarnings(result, 21, isBlackjack = true, bet = 10000)
        assertEquals(15000, earnings)
    }

    @Test
    fun `should calculate earnings for loss`() {
        val result = GameResult.LOSE
        val earnings = GameLogic.calculateEarnings(result, 18, isBlackjack = false, bet = 10000)
        assertEquals(-10000, earnings)
    }

    @Test
    fun `should calculate earnings for draw`() {
        val result = GameResult.DRAW
        val earnings = GameLogic.calculateEarnings(result, 19, isBlackjack = false, bet = 10000)
        assertEquals(0, earnings)
    }
}
