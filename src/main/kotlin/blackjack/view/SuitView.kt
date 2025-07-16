package blackjack.view

import blackjack.model.Suit

enum class SuitView(
    val suit: Suit,
    val symbol: String,
) {
    HEARTS(Suit.HEARTS, "♥"),
    DIAMONDS(Suit.DIAMONDS, "♦"),
    CLUBS(Suit.CLUBS, "♣"),
    SPADES(Suit.SPADES, "♠"),
    ;

    companion object {
        fun fromSuit(suit: Suit): SuitView = entries.first { it.suit == suit }
    }
}
