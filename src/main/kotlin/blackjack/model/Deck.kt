package blackjack.model

class Deck(private val cards: MutableList<Card> = Card.ALL_CARDS.shuffled().toMutableList()) {
    val size: Int
        get() = cards.size

    val currentCards: List<Card>
        get() = cards.toList()

    fun drawCard(): Card {
        if (cards.isEmpty()) {
            cards.addAll(Card.ALL_CARDS.shuffled())
        }
        return cards.removeFirst()
    }
}
