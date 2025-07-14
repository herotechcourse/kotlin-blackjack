package blackjack.model

class Deck {
    private val cards: MutableList<Card> =
        Suit
            .entries
            .flatMap { suit ->
                Rank.entries.map { rank -> Card(rank, suit) }
            }.shuffled().toMutableList()

    fun draw(count: Int = 1): List<Card> {
        require(count > 0) { "Card count must be positive." }
        require(cards.size >= count) { "Not enough cards in the deck!" }
        return (1..count).map { cards.removeAt(0) }
    }

    fun countCards(): Int = cards.size
}
