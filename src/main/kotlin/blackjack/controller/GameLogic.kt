package blackjack.controller

import blackjack.model.BettingInfo
import blackjack.model.GameResult
import blackjack.view.InputView

object GameLogic {

    fun getBettingInfo(): List<BettingInfo> {
        val playerNames = InputView.askPlayerNames()
        return playerNames.map { name ->
            BettingInfo(name, InputView.getBettingAmount(name))
        }
    }

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