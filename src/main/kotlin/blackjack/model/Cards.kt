package blackjack.model

class Cards(
    private val _cards: MutableList<Card> = mutableListOf(),
) {
    val cards: List<Card>
        get() = _cards.toList()

    fun addAll(newCards: List<Card>) = _cards.addAll(newCards)

    fun take(count: Int): List<Card> = _cards.take(count)

    fun removeAll(cardsToRemove: List<Card>) = cardsToRemove.forEach { _cards.remove(it) }

    fun shuffle() = _cards.shuffle()

    val size: Int
        get() = _cards.size
}
