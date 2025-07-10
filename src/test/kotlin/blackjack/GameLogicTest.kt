package blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameLogicTest {

    @Test
    fun `returns WIN when player has higher score and no one is busted`() {
        val result = GameLogic.getGameResult(
            playerScore = 20,
            dealerScore = 18,
            isPlayerBusted = false,
            isDealerBusted = false
        )
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `returns LOSE when player is busted`() {
        val result = GameLogic.getGameResult(
            playerScore = 22,
            dealerScore = 17,
            isPlayerBusted = true,
            isDealerBusted = false
        )
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `returns DRAW when scores are equal and no one is busted`() {
        val result = GameLogic.getGameResult(
            playerScore = 20,
            dealerScore = 20,
            isPlayerBusted = false,
            isDealerBusted = false
        )
        assertEquals(GameResult.DRAW, result)
    }
}