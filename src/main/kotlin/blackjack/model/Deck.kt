package blackjack.model

class Deck {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards

    init {
        generateShuffledCards()
    }

    fun drawCards(count: Int = 1): List<Card> {
        if (cards.size == 1) generateShuffledCards()
        val drawnCards = cards.take(count)
        removeCards(drawnCards)
        return drawnCards
    }

    private fun generateShuffledCards() {
        generateCards()
        _cards.shuffle()
    }

    private fun removeCards(element: List<Card>) {
        _cards -= element
    }

    private fun generateCards() {
        _cards.addAll(
            Suit.entries.flatMap { suit ->
                Rank.entries.map { rank ->
                    Card(rank, suit)
                }
            }
        )
    }
}
