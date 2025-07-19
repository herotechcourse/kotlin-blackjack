package blackjack.model

data class Deck(private val cards: MutableList<Card> = Card.ALL_CARDS.shuffled().toMutableList()) {
    val size: Int
        get() = cards.size

    fun drawCard(): Card {
        if (cards.isEmpty()) {
            cards.addAll(Card.ALL_CARDS.shuffled())
        }
        return cards.removeFirst()
    }
}
