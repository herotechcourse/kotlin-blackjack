package blackjack.model

class Dealer(
    override val name: String = "Dealer",
    override var handCards: HandCards = HandCards(),
) : Participant() {

    fun shouldDraw(): Boolean {
        return handCards.total <= Rules.DEALER_DRAW_THRESHOLD
    }

    override fun toString(): String {
        return name
    }
}
