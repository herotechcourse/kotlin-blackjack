package blackjack.model

import blackjack.utils.Constants

abstract class Playable {
    abstract val name: String
    protected var handInternal: Hand = Hand()
    open val hand: Hand
        get() = handInternal

    open fun requestCard(condition: () -> Boolean): Boolean {
        return condition()
    }

    open fun drawCard(newCard: Card) {
        val deque = ArrayDeque(hand.cards)
        deque.addLast(newCard)
        handInternal = Hand(deque.toList())
    }

    open fun calculateHand(): Int {
        return handInternal.calculateCards()
    }

    fun isBust(): Boolean {
        return calculateHand() > Constants.BLACK_JACK
    }

    fun canContinue(): Boolean {
        return calculateHand() <= Constants.BLACK_JACK
    }
}
