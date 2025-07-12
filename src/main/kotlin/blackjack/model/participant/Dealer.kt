package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Deck
import blackjack.model.result.DealerResultTracker

class Dealer(name: String = "Dealer", internal val deck: Deck = Deck.generateADeck()) :
    Participant(name, DealerResultTracker()) {
    private var showAllCards = false
    val result: String
        get() = resultTracker.toString()

    fun shuffleDeck() = deck.shuffle()

    fun dealCard(): Card = deck.drawCard()

    fun shouldNotStand(): Boolean = hand.getScore() <= DEALER_STAND

    fun showAllCards() {
        showAllCards = true
    }

    override fun toString(): String =
        when {
            showAllCards -> "$name's cards: $hand"
            hand.cards.isEmpty() -> "$name has no cards yet."
            else -> "$name: ${hand.cards[0]}"
        }

    companion object {
        const val DEALER_STAND = 16
    }
}
