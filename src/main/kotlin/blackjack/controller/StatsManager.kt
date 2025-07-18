package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Playable
import blackjack.model.Player
import blackjack.view.StatsView

class StatsManager(players: List<Player>, private val dealer: Dealer) {
    private var _winStatistics = StatsView(players, dealer)
    val winStatistics: StatsView get() = _winStatistics

    private var _earnings: Map<Playable, Int> = emptyMap()
    val earnings: Map<Playable, Int> get() = _earnings

    fun processStatistics(
        players: List<Player>,
        dealer: Dealer,
    ) {
        _winStatistics = StatsView(players, dealer)
        updateEarnings()
    }

    private fun updateEarnings() {
        val result = mutableMapOf<Playable, Int>()
        var dealerTotal = 0

        for ((player, resultCode) in _winStatistics.playerBoard) {
            val earning = player.earnings(resultCode)
            result[player] = earning
            dealerTotal -= earning
        }

        result[dealer] = dealerTotal
        _earnings = result.toMap()
    }
}
