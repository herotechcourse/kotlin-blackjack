package blackjack.model

class Deck() {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    init {
        create()
        shuffle()
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

    private fun shuffle() = _cards.shuffle()

    fun drawCard(): Card {
        check(_cards.isNotEmpty()) { "[FATAL]: Deck cards should not be empty." }

        return _cards.removeLast()
    }
}
