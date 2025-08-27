package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

class Running(hand: Hand, deck: Deck) : Active(hand, deck) {
    override fun run(): State {
        hand.addCard(deck.drawCard())
        return evaluateNextState()
    }

    fun evaluateNextState(): State {
        val sum = hand.sumCards()

        return when {
            sum == 21 -> Blackjack(hand, deck)
            sum > 21 -> Busted(hand, deck)
            else -> this
        }
    }

    override fun shouldDraw(goalNumber: Int): Boolean {
        return hand.sumCards() < goalNumber
    }
}
