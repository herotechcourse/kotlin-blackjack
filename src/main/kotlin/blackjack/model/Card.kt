package blackjack.model

data class Card(val suit: Suit, val rank: Rank) {
    companion object {
        val ALL_CARDS: List<Card> by lazy {
            Suit.entries.flatMap { suit ->
                Rank.entries.map { rank ->
                    Card(suit, rank)
                }
            }
        }
    }

    override fun toString(): String = "${rank.symbol}${suit.symbol}"
}
