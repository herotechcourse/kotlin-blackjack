package model

import service.PlayerEarningResult

class Players(val players: List<Player>) {
    init {
        require(players.size <= 6) { "Maximum player names must be 6" }
    }

    fun appendEarnings(
        results: List<PlayerEarningResult>,
        dealer: Dealer,
    ) {
        results.forEach { earning ->
            players[earning.playerId].earnings += earning.earningsChange
            dealer.earnings += earning.dealerEarningChange
        }
    }
}
