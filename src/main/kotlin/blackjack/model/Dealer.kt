package blackjack.model

class Dealer(name: String = "Dealer") : Participant(name) {
    val deck = Deck()
    private var showAllCards = false

    fun dealCard(): Card {
        return deck.drawCard()
    }

    fun shouldNotStand(): Boolean = hand.getScore() <= DEALER_STAND

    fun showAllCards() {
        showAllCards = true
    }

    override fun toString(): String = when {
        showAllCards -> "$name's cards: $hand"
        hand.dealtCards.isEmpty() -> "$name has no cards yet."
        else -> "$name: ${hand.dealtCards[0]}"
    }

    companion object {
        const val DEALER_STAND = 16
    }
}
