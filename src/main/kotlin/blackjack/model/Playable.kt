package blackjack.model

import blackjack.utils.Constants

interface Playable {
    val name: String
    val hand: Hand

    fun requestCard(condition: () -> Boolean): Boolean {
        return condition()
    }

    fun drawCard(newCard: Card)

    fun calculateHand(): Int

    fun isBust(): Boolean {
        return calculateHand() > Constants.BLACK_JACK
    }

    fun canContinue(): Boolean {
        return calculateHand() <= Constants.BLACK_JACK
    }
}
