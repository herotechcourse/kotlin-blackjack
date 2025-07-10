package blackjack.model

data class Card(val rank: Rank, val suit: Suit) {
    override fun toString(): String {
        return Rank.toString(rank) + suit.symbol
    }
}
