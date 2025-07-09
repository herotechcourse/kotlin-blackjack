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
        if (_cards.isEmpty()) throw IllegalStateException()

        return _cards.removeLast()
    }

}
