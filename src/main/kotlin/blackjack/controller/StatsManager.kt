package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Stats
import blackjack.view.OutputView

class StatsManager(players: List<Player>, dealer: Dealer) {
    var winStatistics = Stats(players, dealer)

    fun processStatistics(
        players: List<Player>,
        dealer: Dealer,
    ) {
        winStatistics = Stats(players, dealer)
        winStatistics.updateDealerStats()
        OutputView.displayFinalResults(winStatistics)
    }
}
