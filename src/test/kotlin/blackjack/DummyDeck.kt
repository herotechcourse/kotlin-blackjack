package blackjack

import blackjack.model.GameConstants
import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.card.Suit
import blackjack.model.holder.CardHolder
import blackjack.model.participant.Participant

class DummyDeck : CardHolder() {
    fun hit(
        participant: Participant,
        count: Int = GameConstants.DEALER_FIRST_HIT_COUNT,
    ) {
        participant.receive(this.draw(count))
    }

    companion object {
        private fun initShuffledDeck(): List<Card> {
            return initDeck().shuffled()
        }

        internal fun initDeck(): List<Card> {
            return Suit.entries.flatMap { suit -> Rank.entries.map { rank -> Card(suit, rank) } }
        }
    }
}
