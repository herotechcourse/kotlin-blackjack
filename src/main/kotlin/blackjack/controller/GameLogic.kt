package blackjack.controller

import blackjack.model.GameResult

object GameLogic {

    fun getGameResult(
        playerScore: Int,
        dealerScore: Int,
        isPlayerBusted: Boolean,
        isDealerBusted: Boolean
    ): GameResult {
        return when {
            isPlayerBusted -> GameResult.LOSE
            isDealerBusted -> GameResult.WIN
            playerScore > dealerScore -> GameResult.WIN
            playerScore < dealerScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }
}