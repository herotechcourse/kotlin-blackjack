package blackjack.model

class Stats(val players: List<Player>, val dealer: Dealer) {
    var dealerStats = mapOf(Result.WIN to 0, Result.LOSE to 0, Result.TIE to 0)

    fun updateDealerStats() {
        calculatePlayerResults()
        val stats = dealerStats.toMutableMap()
        val winCount = players.count { it.result == Result.LOSE }
        val loseCount = players.count { it.result == Result.WIN }
        val tieCount = players.count { it.result == Result.TIE }
        stats[Result.WIN] = winCount
        stats[Result.LOSE] = loseCount
        stats[Result.TIE] = tieCount
        dealerStats = stats.toMap()
    }

    private fun calculatePlayerResults() {
        players.forEach { updatePlayerResult(it) }
    }

    private fun updatePlayerResult(player: Player) {
        val playerScore = player.calculateHand()
        val dealerScore = dealer.calculateHand()
        when {
            player.isBust() -> player.result = Result.LOSE
            dealer.isBust() -> player.result = Result.WIN
            playerScore > dealerScore -> player.result = Result.WIN
            playerScore < dealerScore -> player.result = Result.LOSE
            else -> player.result = Result.TIE
        }
    }
}
