package blackjack.model

class Dealer(name: String = "Dealer") : Participant(name) {
    val deck = Deck()
    var showAll = false

    fun dealCard(): Card {
        return deck.drawCard()
    }

    fun shouldStand(): Boolean = hand.getScore() > DEALER_STAND

    override fun toString(): String = when {
        showAll -> "$name's cards: $hand"
        hand.dealtCards.isEmpty() -> "$name has no cards yet."
        else -> "$name: ${hand.dealtCards[0]}"
    }

    companion object {
        const val DEALER_STAND = 16
    }
}
