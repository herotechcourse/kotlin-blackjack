package blackjack

import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.card.Suit
import blackjack.model.holder.CardHolder

object TestFixture {
    object DoesNotHasAce {
        val FIVE_CARDS_SUM_21 =
            listOf(
                Card(Suit.HEART, Rank.TWO),
                Card(Suit.DIAMOND, Rank.THREE),
                Card(Suit.SPADE, Rank.FOUR),
                Card(Suit.CLUB, Rank.FIVE),
                Card(Suit.HEART, Rank.SEVEN),
            )

        val THREE_CARDS_SUM_21 =
            listOf(
                Card(Suit.HEART, Rank.SEVEN),
                Card(Suit.SPADE, Rank.SEVEN),
                Card(Suit.DIAMOND, Rank.SEVEN),
            )

        val THREE_CARDS_SUM_16 =
            listOf(
                Card(Suit.DIAMOND, Rank.TWO),
                Card(Suit.DIAMOND, Rank.QUEEN),
                Card(Suit.DIAMOND, Rank.FOUR),
            )

        val FOUR_CARDS_SUM_25 =
            listOf(
                Card(Suit.DIAMOND, Rank.TWO),
                Card(Suit.DIAMOND, Rank.QUEEN),
                Card(Suit.DIAMOND, Rank.FOUR),
                Card(Suit.DIAMOND, Rank.NINE),
            )
    }

    object TwoCards {
        val TWO_CARDS_BLACKJACK =
            listOf(
                Card(Suit.DIAMOND, Rank.ACE),
                Card(Suit.DIAMOND, Rank.QUEEN),
            )

        val TWO_CARDS_SUM_20 =
            listOf(
                Card(Suit.DIAMOND, Rank.ACE),
                Card(Suit.DIAMOND, Rank.NINE),
            )
        val TWO_CARDS_SUM_17 =
            listOf(
                Card(Suit.DIAMOND, Rank.ACE),
                Card(Suit.DIAMOND, Rank.SIX),
            )
    }

    object BustedWithLastCard {
        val THREE_CARDS_SUM_24 =
            listOf(
                Card(Suit.DIAMOND, Rank.JACK),
                Card(Suit.DIAMOND, Rank.QUEEN),
                Card(Suit.DIAMOND, Rank.FOUR),
            )
    }

    object Card {
        val DIAMOND_JACK = Card(Suit.DIAMOND, Rank.JACK)
        val HEART_ACE = Card(Suit.HEART, Rank.ACE)
    }

    fun CardHolder.first(): blackjack.model.card.Card = cards.first()

    fun CardHolder.last(): blackjack.model.card.Card = cards.last()
}
