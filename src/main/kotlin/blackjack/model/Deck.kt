package blackjack.model

class Deck {
    var cards: MutableList<Card> = mutableListOf()
        private set

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
        cards.shuffle()
    }

    private fun removeCards(element: List<Card>) {
        cards -= element
    }

    private fun generateCards() {
        cards =
            Suit.entries.map { suit ->
                Rank.entries.map { rank ->
                    Card(rank, suit)
                }
            }.flatten().toMutableList()
    }
}
