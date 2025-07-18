package blackjack.model

class FinalResult(val dealer: Player, private val players: List<Player>) {
    companion object {
        const val INITIAL_EARNING = 0.0
        const val LOSS_RATE = -1.0
        const val TIE_RATE = 0.0
        const val WIN_RATE = 1.0
        const val BLACKJACK_RATE = 1.5
    }

    internal fun updateEarnings() {
        var sumOfPlayerEarning = INITIAL_EARNING
        for (player in players) {
            val newEarning = player.betAmount * getPayoutRate(player, this.dealer)
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
        return when {
            player.status.isBusted -> LOSS_RATE
            dealer.status.isBlackjack && player.status.isBlackjack -> TIE_RATE
            dealer.status.isBlackjack && !player.status.isBlackjack -> LOSS_RATE
            player.status.isBlackjack && !dealer.status.isBlackjack -> BLACKJACK_RATE
            player.status.isNeitherBlackjackNorBusted && dealer.status.isNeitherBlackjackNorBusted
            -> getRateWithBenchMarkScore(player, dealer)
            else -> WIN_RATE
        }
    }

    private fun getRateWithBenchMarkScore(
        player: Player,
        dealer: Player,
    ): Double {
        val benchMarkScore = dealer.score
        return when {
            player.score == benchMarkScore -> TIE_RATE
            player.score > benchMarkScore -> WIN_RATE
            else -> LOSS_RATE
        }
    }
}
