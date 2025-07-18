package blackjack.blackjackgame

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.OutputView

object TurnManager {
    fun handlePlayerTurn(
        dealer: Dealer,
        player: Player,
    ) {
        if (player.isBlackJack()) {
            OutputView.displayBlackjackMessage(player.name)
            return
        }
        dealer.askPlayerDraw(player)
    }
}
