package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Stats
import blackjack.view.InputView
import blackjack.view.OutputView

class GameManager {
    val cardManager = CardManager()
    val playerManager = PlayerManager()
    val dealer = Dealer()
    var winStatistics = Stats(playerManager.players, dealer)

    fun run() {
        val names = InputView.readPlayerNames()
        names.forEach { name -> playerManager.addPlayer(name) }

        repeat(2) {
            playerManager.players.forEach { player ->
                player.drawCard(cardManager.giveCard())
            }
            dealer.drawCard(cardManager.giveCard())
        }

        OutputView.displayInitialState(playerManager.players, dealer)

        playerManager.players.forEach { player ->
            playerManager.askPlayerHit(player) { cardManager.giveCard() }
        }

        while (dealer.shouldDrawCardOrNot()) {
            dealer.drawCard(cardManager.giveCard())
            OutputView.displayDealerDrawsCard()
        }

        OutputView.displayFinalState(playerManager.players, dealer)

        winStatistics = Stats(playerManager.players, dealer)
        winStatistics.updateDealerStats()
        OutputView.displayFinalResults(winStatistics)
    }
}
