package blackjack.model

class Deck {
    var cards: MutableList<Card> = mutableListOf()
        private set

    init {
        generateCards()
        cards.shuffle()
    }

    fun drawCards(count: Int = 1): List<Card> {
        val drawnCards = cards.take(count)
        removeCards(drawnCards)
        return drawnCards
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
