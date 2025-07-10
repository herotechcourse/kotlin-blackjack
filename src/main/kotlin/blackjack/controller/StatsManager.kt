package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Stats

class StatsManager(players: List<Player>, dealer: Dealer) {
    private var _winStatistics = Stats(players, dealer)
    val winStatistics: Stats = _winStatistics

    fun processStatistics(
        players: List<Player>,
        dealer: Dealer,
    ) {
        _winStatistics = Stats(players, dealer)
        _winStatistics.updateDealerStats()
    }
}
