package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Stats
import blackjack.view.InputView
import blackjack.view.OutputView

class GameManager {
    val cardManager = CardManager()
    val playerManager = PlayerManager()
    val dealerManager = DealerManager()
    var winStatistics = Stats(playerManager.players, dealerManager.dealer)

    fun run() {
        val names = InputView.readPlayerNames()
        names.forEach { name -> playerManager.addPlayer(name) }

        repeat(2) {
            playerManager.players.forEach { player ->
                player.drawCard(cardManager.giveCard())
            }
            dealerManager.dealer.drawCard(cardManager.giveCard())
        }

        OutputView.displayInitialState(playerManager.players, dealerManager.dealer)

        playerManager.players.forEach { player ->
            playerManager.askPlayerHit(player) { cardManager.giveCard() }
        }

        while (dealerManager.dealer.shouldDrawCardOrNot()) {
            dealerManager.dealer.drawCard(cardManager.giveCard())
            OutputView.displayDealerDrawsCard()
        }

        OutputView.displayFinalState(playerManager.players, dealerManager.dealer)

        winStatistics = Stats(playerManager.players, dealerManager.dealer)
        winStatistics.updateDealerStats()
        OutputView.displayFinalResults(winStatistics)
    }
}
