package blackjack.model

class Dealer(gamblerInfo: GamblerInfo) : Player(gamblerInfo) {
    fun isDealerBelowMinScore(): Boolean = score <= DEALER_MIN_SCORE

    companion object {
        private const val DEALER_MIN_SCORE = 16
    }
}
