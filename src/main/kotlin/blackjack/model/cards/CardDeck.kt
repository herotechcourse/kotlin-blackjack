package blackjack.model.cards

class CardDeck(
    val deck: ArrayDeque<Card> = ArrayDeque(),
) {
    init {
        generateDeck()
        this.shuffle()
    }

    val size: Int
        get() = deck.size

    private fun generateDeck() {
        Suit.entries.forEach { suit ->
            Rank.entries.forEach { rank ->
                deck.addFirst(Card(rank, suit))
            }
        }
    }

    private fun CardDeck.shuffle() {
        this.deck.shuffle()
    }

    fun hit(): Card {
        return deck.removeFirst()
    }
}
