package blackjack.model

data class Card(val rank: Rank, val suit: Suit) {
    val value: Int get() = rank.value

    override fun toString(): String {
        return "${rank.digit}${suit.symbol}"
    }
}
