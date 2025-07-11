package blackjack.model.holder

import blackjack.model.card.Card

abstract class CardHolder {
    protected val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards

    abstract val name: String

    /** fallback when cannot draw */
    abstract fun onDrawFailed(): Card

    fun receiveCard(card: Card) {
        _cards.add(card)
    }

    fun drawCard(): Card {
        return if (_cards.isNotEmpty()) {
            _cards.removeAt(0)
        } else {
            onDrawFailed()
        }
    }
}
