package blackjack.model.holder

import blackjack.model.card.Card

abstract class CardHolder {
    protected val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards

    fun size() : Int = cards.size

    /** fallback when cannot draw */
    abstract fun onDrawFailed(): Card

    fun receive(card: Card) {
        _cards.add(card)
    }

    fun peek(): Card = cards.first()

    /**
     * @throws IllegalStateException if try failed and should throw error
     *
     */
    fun draw(): Card {
        return if (_cards.isNotEmpty()) {
            _cards.removeAt(0)
        } else {
            onDrawFailed()
        }
    }
}
