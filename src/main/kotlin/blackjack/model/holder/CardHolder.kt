package blackjack.model.holder

import blackjack.model.card.Card

abstract class CardHolder {
    protected var currentCards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = currentCards

    fun cardsCount(): Int = cards.size

    /** fallback when cannot draw */
    abstract fun onDrawFailed(): Card

    open fun receive(card: Card): Boolean {
        currentCards.add(card)
        return true
    }

    fun first(): Card = cards.first()

    fun last(): Card = cards.last()

    /**
     * @throws IllegalStateException if try failed and should throw error
     *
     */
    fun draw(): Card {
        return if (currentCards.isNotEmpty()) {
            currentCards.removeFirst()
        } else {
            onDrawFailed()
        }
    }
}
