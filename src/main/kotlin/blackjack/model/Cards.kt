package blackjack.model

class Cards(
    private val cards: MutableList<Card> = mutableListOf(),
) {
    val cardList: List<Card>
        get() = cards.toList()

    fun addAll(newCards: List<Card>) = cards.addAll(newCards)

    fun take(count: Int): List<Card> = cards.take(count)

    fun removeAll(cardsToRemove: List<Card>) = cardsToRemove.forEach { cards.remove(it) }

    fun shuffle() = cards.shuffle()

    val size: Int
        get() = cards.size
}
