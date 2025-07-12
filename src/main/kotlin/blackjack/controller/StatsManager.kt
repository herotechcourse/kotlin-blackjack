package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.StatsView

class StatsManager(players: List<Player>, dealer: Dealer) {
    private var _winStatistics = StatsView(players, dealer)
    val winStatistics: StatsView = _winStatistics

    fun processStatistics(
        players: List<Player>,
        dealer: Dealer,
    ) {
        _winStatistics = StatsView(players, dealer)
        _winStatistics.updateDealerStats()
    }
}
