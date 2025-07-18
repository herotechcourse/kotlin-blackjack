package blackjack

import blackjack.model.Card
import blackjack.model.Rank
import blackjack.model.Suit

object TestCards {
    val Ace = Card.of(Suit.DIAMONDS, Rank.ACE)
    val TWO = Card.of(Suit.DIAMONDS, Rank.TWO)
    val QUEEN = Card.of(Suit.DIAMONDS, Rank.QUEEN)
    val FOUR = Card.of(Suit.DIAMONDS, Rank.FOUR)
    val JACK = Card.of(Suit.DIAMONDS, Rank.JACK)
}
