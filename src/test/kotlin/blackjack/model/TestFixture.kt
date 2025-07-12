package blackjack.model

import blackjack.model.card.Rank
import blackjack.model.card.Suit

object TestFixture {

    object DoesNotHasAce {
        val TOTAL_SUM_16 = listOf(
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.TWO),
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.QUEEN),
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.FOUR),
        )
    }

    object HasAce {
        val TOTAL_SUM_17 = listOf(
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.ACE),
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.TWO),
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.QUEEN),
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.FOUR),
        )
    }

    object HasTripleAce {
        val TOTAL_SUM_20 = listOf(
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.ACE),
            blackjack.model.card.Card(Suit.HEART, Rank.ACE),
            blackjack.model.card.Card(Suit.SPADES, Rank.ACE),
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.SEVEN),
        )

        val TOTAL_SUM_13 = listOf(
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.ACE),
            blackjack.model.card.Card(Suit.HEART, Rank.ACE),
            blackjack.model.card.Card(Suit.SPADES, Rank.ACE),
            blackjack.model.card.Card(Suit.DIAMONDS, Rank.JACK),
        )
    }

    object Card {
        val DIAMOND_JACK = blackjack.model.card.Card(Suit.DIAMONDS, Rank.JACK)
        val HEART_ACE = blackjack.model.card.Card(Suit.HEART, Rank.ACE)
    }
}