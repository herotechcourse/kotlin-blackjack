package blackjack

import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.card.Suit
import blackjack.model.holder.CardHolder

class DummyDeck : CardHolder() {
    companion object {
        internal fun initShuffledDeck(): List<Card> {
            return initNotShuffledDeck().shuffled()
        }

        internal fun initNotShuffledDeck(): List<Card> {
            return Suit.entries.flatMap { suit -> Rank.entries.map { rank -> Card(suit, rank) } }
        }
    }
}
