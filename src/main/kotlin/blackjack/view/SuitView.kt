package blackjack.view

import blackjack.model.Suit

object SuitView {
    fun toSymbol(suit: Suit): String =
        when (suit) {
            Suit.HEARTS -> "♥"
            Suit.DIAMONDS -> "♦"
            Suit.CLUBS -> "♣"
            Suit.SPADES -> "♠"
            Suit.NONE -> "?"
        }

    fun fromSymbol(symbol: Char): Suit =
        when (symbol) {
            '♥' -> Suit.HEARTS
            '♦' -> Suit.DIAMONDS
            '♣' -> Suit.CLUBS
            '♠' -> Suit.SPADES
            else -> Suit.NONE
        }
}
