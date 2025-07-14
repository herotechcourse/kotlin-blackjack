package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Deck
import blackjack.model.result.DealerResultTracker

class Dealer(name: String = "Dealer", internal val deck: Deck = Deck.generateADeck()) :
    Participant(name, DealerResultTracker()) {
    private var showAllCards = false
    val hand: List<Card>
        get() {
            if (showAllCards) {
                return _hand.cards
            }
            return _hand.cards.takeIf { it.isNotEmpty() }?.let { listOf(it.first()) } ?: emptyList()
        }
    val result: String
        get() = resultTracker.toString()

    fun shuffleDeck() = deck.shuffle()

    fun dealCard(): Card = deck.drawCard()

    fun shouldNotStand(): Boolean = _hand.getScore() <= DEALER_STAND

    fun showAllCards() {
        showAllCards = true
    }

    companion object {
        const val DEALER_STAND = 16
    }
}
