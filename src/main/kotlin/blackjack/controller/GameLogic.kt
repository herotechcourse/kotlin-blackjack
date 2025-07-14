package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Player

object GameLogic {
    fun handlePlayerTurn(dealer: Dealer, player: Player) {
        if (player.isBlackJack()) {
            println("${player.name} has Blackjack!")
            return
        }
        dealer.askPlayerDraw(player)
    }

    fun getGameResult(
        playerScore: Int,
        dealerScore: Int,
        isPlayerBusted: Boolean,
        isDealerBusted: Boolean,
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
