package blackjack.model

class Deck {
    private val deckCards = Cards()

    val cards: List<Card>
        get() = deckCards.cardList

    init {
        generateFullDeck()
        deckCards.shuffle()
    }

    fun drawCard(count: Int = 1): List<Card> {
        val drawnCards = deckCards.take(count)
        deckCards.removeAll(drawnCards)
        return drawnCards
    }

    private fun generateFullDeck() {
        val allCards =
            Suit.entries.flatMap { suit ->
                Rank.entries.map { rank -> Card(rank, suit) }
            }
        deckCards.addAll(allCards)
    }
}
