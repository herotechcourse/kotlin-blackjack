package blackjack.model.holder

import blackjack.model.GameConstants
import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.card.Suit
import blackjack.model.participant.Participant
import blackjack.view.OutputView

class Deck : CardHolder() {
    init {
        _cards = initShuffledDeck().toMutableList()
    }

    override fun onDrawFailed(): Card {
        OutputView.showNewCard()
        _cards = initShuffledDeck().toMutableList()
        return draw()
    }

    fun hit(participant: Participant, count: Int = GameConstants.DEALER_FIRST_HIT_COUNT) {
        val card = this.draw()
        participant.receive(card)
    }

    companion object {
        const val FULL_DECK_SIZE = 52

        private fun initShuffledDeck(): List<Card> {
            return initDeck().shuffled()
        }

        internal fun initDeck(): List<Card> {
            return Suit.entries.flatMap { suit -> Rank.entries.map { rank -> Card(suit, rank) } }
        }
    }
}
