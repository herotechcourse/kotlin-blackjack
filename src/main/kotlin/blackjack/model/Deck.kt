package blackjack.model

class Deck {
    private val _cards = Cards()

    val cards: List<Card>
        get() = _cards.cards

    init {
        generateFullDeck()
        _cards.shuffle()
    }

    fun drawCard(count: Int = 1): List<Card> {
        val drawnCards = _cards.take(count)
        _cards.removeAll(drawnCards)
        return drawnCards
    }

    private fun generateFullDeck() {
        val allCards =
            Suit.entries.flatMap { suit ->
                Rank.entries.map { rank -> Card(rank, suit) }
            }
        _cards.addAll(allCards)
    }
}
