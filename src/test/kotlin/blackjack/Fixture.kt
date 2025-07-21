package blackjack

import blackjack.model.PlayingCard
import blackjack.model.Rank
import blackjack.model.Suit

object Fixture {
    val SPADES_ACE = PlayingCard.of(Rank.ACE, Suit.SPADES)

    val HEARTS_ACE = PlayingCard.of(Rank.ACE, Suit.HEARTS)

    val DIAMONDS_ACE = PlayingCard.of(Rank.ACE, Suit.DIAMONDS)
    val DIAMONDS_TWO = PlayingCard.of(Rank.TWO, Suit.DIAMONDS)
    val DIAMONDS_SIX = PlayingCard.of(Rank.SIX, Suit.DIAMONDS)
    val DIAMONDS_SEVEN = PlayingCard.of(Rank.SEVEN, Suit.DIAMONDS)
    val DIAMONDS_NINE = PlayingCard.of(Rank.NINE, Suit.DIAMONDS)
    val DIAMONDS_TEN = PlayingCard.of(Rank.TEN, Suit.DIAMONDS)
    val DIAMONDS_JACK = PlayingCard.of(Rank.JACK, Suit.DIAMONDS)
    val DIAMONDS_QUEEN = PlayingCard.of(Rank.QUEEN, Suit.DIAMONDS)
}
