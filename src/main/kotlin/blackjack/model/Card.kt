package blackjack.model

import blackjack.view.SuitView
import blackjack.view.RankView

data class Card(val suit: Suit, val rank: Rank) {

    fun getScore(): Int = rank.value

    fun isAce(): Boolean = rank == Rank.ACE

    fun display(): String {
        val suitSymbol = SuitView.from(suit).symbol
        val rankSymbol = RankView.from(rank).symbol
        return "$rankSymbol$suitSymbol"
    }
}

enum class Suit {
    HEART, DIAMOND, CLUB, SPADE
}

enum class Rank(val value: Int) {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);
}

