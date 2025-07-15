package blackjack.model.participant

import blackjack.model.Chips
import blackjack.model.card.Card
import blackjack.model.card.Deck
import blackjack.model.result.DealerResultTracker
import blackjack.model.result.PlayerResultTracker
import blackjack.model.result.Result

class Dealer(
    name: String = "Dealer",
    internal val deck: Deck = Deck.generateADeck(),
    private val resultTracker: DealerResultTracker = DealerResultTracker()
) :
    Participant(name) {
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

    fun recordResult(result: Result) = resultTracker.record(result)

    fun deductFromProfit(chips: Chips) {
        profit -= chips
    }

    companion object {
        const val DEALER_STAND = 16
    }
}
