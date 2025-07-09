package blackjack

data class Card(val rank: Rank, val suit: Suit) {
    override fun toString(): String {
        return super.toString()
    }
}
