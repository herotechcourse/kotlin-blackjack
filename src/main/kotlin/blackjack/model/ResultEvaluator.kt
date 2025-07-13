package blackjack.model

data class DealerResult(var wins: Int = 0, var losses: Int = 0, var draws: Int = 0) {
    override fun toString() = "DealerResult(w=$wins,l=$losses,d=$draws)"
}

data class PlayerResult(var win: Boolean = false, var draw: Boolean = false, val name: String = "") {
    override fun toString() = "PlayerResult($name,w=$win,d=$draw)"
}

class ResultEvaluator(private val players: Players, private val dealer: Dealer) {

    fun calculateResults(): Pair<DealerResult, List<PlayerResult>> {
        return Pair(calculateDealerResults(), calculateAllPlayersResults())
    }

    private fun calculateDealerResults(): DealerResult {
        val dealerResult = DealerResult()
        if (dealer.isBusted()) {
            dealerResult.losses = players.players.count() { it.isNotBusted() }
            dealerResult.wins = players.players.count() { it.isBusted() }
            return dealerResult
        }
        players.forEach {
            compareDealerToPlayer( it, dealerResult)
        }
        return dealerResult
    }

    private fun compareDealerToPlayer(player: Player, dealerResult: DealerResult) {
        if (player.isBusted()){
            dealerResult.wins++
        }
        if (player.isNotBusted()) {
            when {
                player.handCards < dealer.handCards -> dealerResult.wins++
                player.handCards > dealer.handCards -> dealerResult.losses++
                else -> dealerResult.draws++
            }
        }
    }

    private fun calculateAllPlayersResults(): List<PlayerResult> {
        val playerResults = mutableListOf<PlayerResult>()
        players.forEach { playerResults.add(calculatePlayerResult(it)) }
        return playerResults
    }

    private fun calculatePlayerResult(player: Player): PlayerResult {
        if (player.isBusted())
            return PlayerResult(name = player.name)
        if (dealer.isBusted())
            return PlayerResult(win = true, name = player.name)
        return when {
            player.handCards < dealer.handCards -> PlayerResult(name = player.name)
            player.handCards > dealer.handCards -> PlayerResult(win = true, name = player.name)
            else -> PlayerResult(draw = true, name = player.name)
        }
    }
}
