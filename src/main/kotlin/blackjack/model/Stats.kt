package blackjack.model

class Stats(val players: List<Player>, val dealer: Dealer) {
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

    fun payOutPotToEarnings(): Map<Playable, Int> {
        calculatePlayerResults()
        val isDealerBlackjack = dealer.isBlackjack()
        var pot = players.sumOf { it.bet }
        val earningsMap = mutableMapOf<Playable, Int>()

        players.forEach { player ->
            val result = player.result
            val amount = calculatePayout(result, player.bet, isDealerBlackjack)
            pot -= payoutImpactOnPot(result, player.bet, isDealerBlackjack)
            earningsMap[player] = amount
        }

        earningsMap[dealer] = pot
        return earningsMap.toMap()
    }

    private fun calculatePayout(
        result: Result,
        bet: Int,
        dealerBlackjack: Boolean,
    ): Int {
        return if (dealerBlackjack) {
            calculatePayoutWithDealerBlackjack(result, bet)
        } else {
            calculatePayoutWithDealerNonBlackjack(result, bet)
        }
    }

    private fun calculatePayoutWithDealerBlackjack(
        result: Result,
        bet: Int,
    ): Int {
        return when (result) {
            Result.BLACKJACK, Result.TIE -> 0
            Result.LOSE -> -bet
            else -> 0
        }
    }

    private fun calculatePayoutWithDealerNonBlackjack(
        result: Result,
        bet: Int,
    ): Int {
        return when (result) {
            Result.BLACKJACK -> (bet * Result.BLACKJACK_BONUS).toInt()
            Result.WIN -> bet
            Result.TIE -> 0
            Result.LOSE -> -bet
            Result.NONE -> throw IllegalStateException("Player result not calculated")
        }
    }

    private fun payoutImpactOnPot(
        result: Result,
        bet: Int,
        dealerBlackjack: Boolean,
    ): Int {
        return if (dealerBlackjack) {
            payoutImpactOnPotWithDealerBlackjack(result, bet)
        } else {
            payoutImpactOnPotWithDealerNonBlackjack(result, bet)
        }
    }

    private fun payoutImpactOnPotWithDealerBlackjack(
        result: Result,
        bet: Int,
    ): Int {
        return when (result) {
            Result.BLACKJACK, Result.TIE -> bet
            else -> 0
        }
    }

    private fun payoutImpactOnPotWithDealerNonBlackjack(
        result: Result,
        bet: Int,
    ): Int {
        return when (result) {
            Result.BLACKJACK -> bet + (bet * Result.BLACKJACK_BONUS).toInt()
            Result.WIN -> bet * 2
            Result.TIE -> bet
            else -> 0
        }
    }
}
