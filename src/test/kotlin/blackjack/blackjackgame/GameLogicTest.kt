package blackjack.blackjackgame

import blackjack.model.GameResult
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameLogicTest {
    @Test
    fun `returns WIN when player has higher score and no one is busted`() {
        val result =
            GameLogic.getGameResult(
                playerScore = 20,
                dealerScore = 18,
                isPlayerBusted = false,
                isDealerBusted = false,
                isPlayerBlackjack = false,
                isDealerBlackjack = false,
            )
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `returns LOSE when player is busted`() {
        val result =
            GameLogic.getGameResult(
                playerScore = 22,
                dealerScore = 17,
                isPlayerBusted = true,
                isDealerBusted = false,
                isPlayerBlackjack = false,
                isDealerBlackjack = false,
            )
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `returns DRAW when scores are equal and no one is busted`() {
        val result =
            GameLogic.getGameResult(
                playerScore = 20,
                dealerScore = 20,
                isPlayerBusted = false,
                isDealerBusted = false,
                isPlayerBlackjack = false,
                isDealerBlackjack = false,
            )
        assertEquals(GameResult.DRAW, result)
    }

    @Test
    fun `returns DRAW when both player and dealer have Blackjack`() {
        val result =
            GameLogic.getGameResult(
                playerScore = 21,
                dealerScore = 21,
                isPlayerBusted = false,
                isDealerBusted = false,
                isPlayerBlackjack = true,
                isDealerBlackjack = true,
            )
        assertEquals(GameResult.DRAW, result)
    }
}
