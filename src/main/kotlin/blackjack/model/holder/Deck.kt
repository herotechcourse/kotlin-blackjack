package blackjack.model.holder

import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.card.Suit

class Deck : CardHolder() {
    init {
        _cards = initShuffledDeck().toMutableList()
    }

    override fun onDrawFailed(): Card {
        // TODO: output view
        println("Card deck is empty... prepare for a new card deck.")
        _cards = initShuffledDeck().toMutableList()
        return draw()
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
