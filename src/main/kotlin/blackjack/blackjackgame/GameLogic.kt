package blackjack.blackjackgame

import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Player

object GameLogic {
    fun handlePlayerTurn(
        dealer: Dealer,
        player: Player,
    ) {
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
        isPlayerBlackjack: Boolean,
        isDealerBlackjack: Boolean,
    ): GameResult {
        return when {
            isPlayerBusted -> GameResult.LOSE
            isDealerBusted -> GameResult.WIN
            isPlayerBlackjack && isDealerBlackjack -> GameResult.DRAW
            isPlayerBlackjack -> GameResult.WIN
            isDealerBlackjack -> GameResult.LOSE
            playerScore > dealerScore -> GameResult.WIN
            playerScore < dealerScore -> GameResult.LOSE
            else -> GameResult.DRAW
        }
    }
}
