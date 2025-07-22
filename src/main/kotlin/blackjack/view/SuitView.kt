package blackjack.view

import blackjack.model.Suit

enum class SuitView(private val suit: Suit, val symbol: String) {
    HEART(Suit.HEART, "♥"),
    DIAMOND(Suit.DIAMOND, "♦"),
    CLUB(Suit.CLUB, "♣"),
    SPADE(Suit.SPADE, "♠"),
    ;

    companion object {
        fun from(suit: Suit): SuitView {
            return SuitView.entries.first { it.suit == suit }
        }
    }
}
