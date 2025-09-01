package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

class Stay(hand: Hand, deck: Deck) : Finished(hand, deck) {
    override fun earningsAgainst(other: Finished): Double {
        val myPoints = hand.sumCards()
        val theirPoints = other.hand.sumCards()

        return when {
            other is Busted -> 1.0
            myPoints > theirPoints -> 1.0
            myPoints == theirPoints -> 0.0
            else -> -1.0
        }
    }
}
