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
        playerBlackjack: Boolean,
        dealerBlackjack: Boolean,
    ): GameResult {
        return when {
            playerBlackjack && dealerBlackjack -> GameResult.DRAW
            playerBlackjack -> GameResult.WIN
            isPlayerBusted -> GameResult.LOSE
            isDealerBusted -> GameResult.WIN
            playerScore > dealerScore -> GameResult.WIN
            playerScore < dealerScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }
}
