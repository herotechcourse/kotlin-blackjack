package blackjack.model

data class Card(val suit: Suit, val rank: Rank) {
    override fun toString(): String {
        return "${rank.face}${suit.symbol}"
    }
}
