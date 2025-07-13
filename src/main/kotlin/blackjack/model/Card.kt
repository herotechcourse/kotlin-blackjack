package blackjack.model

import blackjack.view.RankView
import blackjack.view.SuitView

data class Card(val rank: Rank, val suit: Suit) {
    val value: Int get() = rank.value

    override fun toString(): String {
        return "${RankView.toSymbol(rank)}${SuitView.toSymbol(suit)}"
    }
}
