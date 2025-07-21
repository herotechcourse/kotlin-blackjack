package blackjack.blackjackgame

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
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

        while (true) {
            val wantsToDraw = InputView.askPlayerWantsToDraw(player.name)
            if (wantsToDraw) {
                dealer.giveCardTo(player)
                OutputView.displayPlayerHand(player)
                if (player.isBusted()) break
            } else {
                break
            }
        }
    }

    fun handleDealerTurn(dealer: Dealer) {
        while (dealer.shouldDraw()) {
            OutputView.displayDealerDraw()
            dealer.giveCardTo(dealer)
            OutputView.displayDealerStatus(dealer)
        }
    }
}
