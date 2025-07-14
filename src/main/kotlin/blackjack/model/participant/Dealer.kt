package blackjack.model.participant

import blackjack.model.Chips
import blackjack.model.card.Card
import blackjack.model.card.Deck
import blackjack.model.result.DealerResultTracker

class Dealer(name: String = "Dealer", internal val deck: Deck = Deck.generateADeck()) :
    Participant(name, DealerResultTracker()) {
    private var showAllCards = false
    val hand: List<Card>
        get() {
            if (showAllCards) {
                return participantHand.cards.toList()
            }
            return participantHand.cards.takeIf { it.isNotEmpty() }?.let { listOf(it.first()) } ?: emptyList()
        }
    val result: String
        get() = resultTracker.toString()

    fun shuffleDeck() = deck.shuffle()

    fun dealCard(): Card = deck.drawCard()

    fun shouldNotStand(): Boolean = participantHand.getScore() <= DEALER_STAND

    fun showAllCards() {
        showAllCards = true
    }

    fun addProfit(playerProfit: Chips) {
        profit = profit + playerProfit
    }

    companion object {
        const val DEALER_STAND = 16
    }
}
