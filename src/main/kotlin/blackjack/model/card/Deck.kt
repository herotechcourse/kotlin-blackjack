package blackjack.model.card

class Deck(initialCards: List<Card> = emptyList()) {
    private val _cards: MutableList<Card> = initialCards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun shuffle() = _cards.shuffle()

    fun drawCard(): Card {
        check(_cards.isNotEmpty()) { "[FATAL]: Deck cards should not be empty." }

        return _cards.removeLast()
    }

    private fun create() {
        val symbols = Card.Suit.entries
        val cardValues = Card.Rank.entries

        symbols.forEach { symbol ->
            cardValues.forEach { value ->
                _cards.add(Card(symbol, value))
            }
        }
    }

    companion object {
        fun generateADeck(): Deck = Deck().apply { create() }
    }
}
