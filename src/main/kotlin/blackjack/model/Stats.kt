package blackjack.model

class Stats(val players: List<Player>, val dealer: Dealer) {
    private fun calculatePlayerResults() {
        players.forEach { updatePlayerResult(it) }
    }

    private fun updatePlayerResult(player: Player) {
        val playerScore = player.hand.calculateHand()
        val dealerScore = dealer.hand.calculateHand()
        when {
            player.hand.isBlackjack -> player.result = Result.BLACKJACK
            player.hand.isBust -> player.result = Result.LOSE
            dealer.hand.isBust -> player.result = Result.WIN
            playerScore > dealerScore -> player.result = Result.WIN
            playerScore < dealerScore -> player.result = Result.LOSE
            else -> player.result = Result.TIE
        }
    }

    fun calculateEarningMapForPlayable(): Map<Playable, Int> {
        calculatePlayerResults()
        val isDealerBlackjack = dealer.hand.isBlackjack
        val earningsMap: MutableMap<Playable, Int> =
            players
                .associateWith { calculateEarningAmount(it.result, it.bet, isDealerBlackjack) }
                .toMutableMap()

        val totalPlayerPayouts = earningsMap.values.sum()

        earningsMap[dealer] = -1 * totalPlayerPayouts
        return earningsMap.toMap()
    }

    private fun calculateEarningAmount(
        result: Result,
        bet: Int,
        dealerBlackjack: Boolean,
    ): Int {
        return if (dealerBlackjack) {
            calculateEarningAmountWithDealerBlackjack(result, bet)
        } else {
            calculateEarningAmountWithDealerNonBlackjack(result, bet)
        }
    }

    private fun calculateEarningAmountWithDealerBlackjack(
        result: Result,
        bet: Int,
    ): Int {
        return when (result) {
            Result.BLACKJACK, Result.TIE -> 0
            else -> -bet
        }
    }

    private fun calculateEarningAmountWithDealerNonBlackjack(
        result: Result,
        bet: Int,
    ): Int {
        return when (result) {
            Result.BLACKJACK -> (bet * Result.BLACKJACK_BONUS_RATE).toInt()
            Result.WIN -> bet
            Result.TIE -> 0
            else -> -bet
        }
    }
}
