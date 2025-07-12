package blackjack.model.holder

import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.card.Suit

object TestFixture {

    object DoesNotHasAce {
        val TOTAL_SUM_16 = listOf(
            Card(Suit.DIAMONDS, Rank.TWO),
            Card(Suit.DIAMONDS, Rank.QUEEN),
            Card(Suit.DIAMONDS, Rank.FOUR),
        )
    }

    object HasAce {
        val TOTAL_SUM_17 = listOf(
            Card(Suit.DIAMONDS, Rank.ACE),
            Card(Suit.DIAMONDS, Rank.TWO),
            Card(Suit.DIAMONDS, Rank.QUEEN),
            Card(Suit.DIAMONDS, Rank.FOUR),
        )
    }

    object HasTripleAce {
        val TOTAL_SUM_20 = listOf(
            Card(Suit.DIAMONDS, Rank.ACE),
            Card(Suit.HEART, Rank.ACE),
            Card(Suit.SPADES, Rank.ACE),
            Card(Suit.DIAMONDS, Rank.SEVEN),
        )

        val TOTAL_SUM_13 = listOf(
            Card(Suit.DIAMONDS, Rank.ACE),
            Card(Suit.HEART, Rank.ACE),
            Card(Suit.SPADES, Rank.ACE),
            Card(Suit.DIAMONDS, Rank.JACK),
        )
    }

    object Card {
        val DIAMOND_JACK = Card(Suit.DIAMONDS, Rank.JACK)
        val HEART_ACE = Card(Suit.HEART, Rank.ACE)
    }
}