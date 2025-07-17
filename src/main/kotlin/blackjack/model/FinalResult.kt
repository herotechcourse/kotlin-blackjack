package blackjack.model

class FinalResult(val dealer: Player, private val players: List<Player>) {
    internal fun updateEarnings() {
        var sumOfPlayerEarning = 0.0
        for (player in players) {
            var newEarning = player.betAmount * getPayoutRate(player, this.dealer)
            player.updateEarning(newEarning)
            sumOfPlayerEarning += newEarning
        }
        this.dealer.updateEarning(-sumOfPlayerEarning)
    }

    private fun getPayoutRate(
        player: Player,
        dealer: Player,
    ): Double {
        player.updateStatus()
        dealer.updateStatus()
        return if (player.status.isBusted) {
            -1.0
        } else if (dealer.status.isBlackjack && player.status.isBlackjack) {
            0.0
        } else if (dealer.status.isBlackjack && !player.status.isBlackjack) {
            -1.0
        } else if (player.status.isBlackjack && !dealer.status.isBlackjack) {
            1.5
        } else if (player.status.isNeitherBlackjackNorBusted && dealer.status.isNeitherBlackjackNorBusted) {
            getRateWithBenchMarkScore(player, dealer)
        } else {
            1.0
        }
    }

    private fun getRateWithBenchMarkScore(
        player: Player,
        dealer: Player,
    ): Double {
        val benchMarkScore = dealer.score
        return if (player.score == benchMarkScore) {
            0.0
        } else if (player.score > benchMarkScore) {
            1.0
        } else {
            -1.0
        }
    }
}
