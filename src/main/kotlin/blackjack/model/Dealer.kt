package blackjack.model

class Dealer : Participant("Dealer") {
    fun mustDraw(): Boolean = total() <= DEALER_DRAW_LIMIT

    companion object {
        const val DEALER_DRAW_LIMIT = 16
    }
}
