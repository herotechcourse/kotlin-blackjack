package blackjack.model

class Deck {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        for (suit in Suit.entries) {
            for (rank in Rank.entries) {
                cards.add(Card(suit, rank))
            }
        }
        cards.shuffle()
    }

    fun drawCard(): Card {
        if (cards.isEmpty()) throw IllegalStateException("Deck is empty")
        return cards.removeFirst()
    }

    fun remainingCards(): Int = cards.size
}
