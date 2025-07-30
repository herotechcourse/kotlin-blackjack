package blackjack.model

import blackjack.model.table.Dealer
import blackjack.model.table.Participant
import blackjack.model.table.Players

class Results(private val dealer: Dealer, private val players: Players) {
    fun calculate(): Pair<Double, Map<Participant, Double>> {
        var dealerResult = 0.0
        val playersResult = emptyMap<Participant, Double>().toMutableMap()

        players.forEach {
            val playerResult = it.state.profit(it.bet, dealer.state)
            dealerResult -= playerResult
            playersResult[it] = playerResult
        }
        return Pair(dealerResult, playersResult)
    }
}
