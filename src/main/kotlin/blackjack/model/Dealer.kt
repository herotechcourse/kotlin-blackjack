package blackjack.model

class Dealer(gamblerInfo: GamblerInfo) : Player(gamblerInfo) {
    fun isDealerBelowMinScore(): Boolean = score <= DEALER_MIN_SCORE

    fun calculateAndSetWinnings(winnings: List<Double>) {
        playerBet.winnings = winnings.sumOf { -it }
    }

    companion object {
        private const val DEALER_MIN_SCORE = 16
    }
}
