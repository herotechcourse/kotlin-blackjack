package blackjack.model

class FinalResult(val dealer: Player, private val players: Players) {
    companion object {
        const val INITIAL_EARNING = 0.0
        const val LOSS_RATE = -1.0
        const val TIE_RATE = 0.0
        const val WIN_RATE = 1.0
        const val BLACKJACK_RATE = 1.5
    }

    fun updateEarnings() {
        var sumOfPlayerEarning = INITIAL_EARNING
        players.forEach { player ->
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
            player.status is Status.Busted -> LOSS_RATE
            dealer.status is Status.Blackjack && player.status is Status.Blackjack -> TIE_RATE
            dealer.status is Status.Blackjack && player.status !is Status.Blackjack -> LOSS_RATE
            player.status is Status.Blackjack -> BLACKJACK_RATE
            dealer.status is Status.Busted -> WIN_RATE
            player.status is Status.Normal && dealer.status is Status.Normal
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
