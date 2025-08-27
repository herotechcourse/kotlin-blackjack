package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

interface State {
    val hand: Hand
    val deck: Deck

    fun run(): State

    fun stay(): State

    fun canTransitionToBlackjack(): Boolean

    fun shouldDraw(goalNumber: Int): Boolean {
        return false
    }
}
