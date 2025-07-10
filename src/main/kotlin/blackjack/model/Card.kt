package blackjack.model

data class Card(
    val rank: Rank,
    val suit: Suit,
) {
    override fun toString(): String {
        return rank.title + suit.suit
    }
}
