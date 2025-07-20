package blackjack.model.holder

import blackjack.model.card.Card

abstract class CardHolder {
    protected var currentCards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = currentCards

    fun cardsCount(): Int = cards.size

    open fun receive(cards: List<Card>): Boolean {
        currentCards.addAll(cards)
        return true
    }

    fun draw(count: Int): List<Card> {
        if (currentCards.isEmpty() || currentCards.size < count) {
            onDrawFailed()
        }
        val cards = currentCards.take(count)
        repeat(count) { currentCards.removeFirst() }
        return cards
    }

    /** fallback when cannot draw */
    fun onDrawFailed(): List<Card> {
        throw IllegalStateException("EMPTY_BUT_TRY_TO_DRAW")
    }
}
