package blackjack

class Deck {

    private val cards: MutableList<Card> = mutableListOf()

    init {
        for (suit in Suit.values()) {
            for (rank in Rank.values()) {
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
