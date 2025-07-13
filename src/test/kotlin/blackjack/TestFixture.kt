package blackjack

import blackjack.model.card.Rank
import blackjack.model.card.Suit

object TestFixture {
    object DoesNotHasAce {
        val TOTAL_SUM_16 =
            listOf(
                blackjack.model.card.Card(Suit.DIAMOND, Rank.TWO),
                blackjack.model.card.Card(Suit.DIAMOND, Rank.QUEEN),
                blackjack.model.card.Card(Suit.DIAMOND, Rank.FOUR),
            )

        val TOTAL_SUM_25 =
            listOf(
                blackjack.model.card.Card(Suit.DIAMOND, Rank.TWO),
                blackjack.model.card.Card(Suit.DIAMOND, Rank.QUEEN),
                blackjack.model.card.Card(Suit.DIAMOND, Rank.FOUR),
                blackjack.model.card.Card(Suit.DIAMOND, Rank.NINE),
            )
    }

    object HasAce {
        val TOTAL_SUM_17 =
            listOf(
                blackjack.model.card.Card(Suit.DIAMOND, Rank.ACE),
                blackjack.model.card.Card(Suit.DIAMOND, Rank.TWO),
                blackjack.model.card.Card(Suit.DIAMOND, Rank.QUEEN),
                blackjack.model.card.Card(Suit.DIAMOND, Rank.FOUR),
            )
    }

    object HasTripleAce {
        val TOTAL_SUM_20 =
            listOf(
                blackjack.model.card.Card(Suit.DIAMOND, Rank.ACE),
                blackjack.model.card.Card(Suit.HEART, Rank.ACE),
                blackjack.model.card.Card(Suit.SPADE, Rank.ACE),
                blackjack.model.card.Card(Suit.DIAMOND, Rank.SEVEN),
            )

        val TOTAL_SUM_13 =
            listOf(
                blackjack.model.card.Card(Suit.DIAMOND, Rank.ACE),
                blackjack.model.card.Card(Suit.HEART, Rank.ACE),
                blackjack.model.card.Card(Suit.SPADE, Rank.ACE),
                blackjack.model.card.Card(Suit.DIAMOND, Rank.JACK),
            )
    }

    object Card {
        val DIAMOND_JACK = blackjack.model.card.Card(Suit.DIAMOND, Rank.JACK)
        val HEART_ACE = blackjack.model.card.Card(Suit.HEART, Rank.ACE)
    }
}
