package blackjack.model

import blackjack.controller.isHitOrStand

data class Card(val rank: Rank, val suit: Suit) {
    override fun toString(): String {
        return Rank.toString(rank) + suit.symbol
    }
}
