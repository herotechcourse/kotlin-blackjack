package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

class Blackjack(hand: Hand, deck: Deck) : Finished(hand, deck) {
    override fun earningsAgainst(other: Finished): Double {
        return when (other) {
            is Busted -> 1.5
            is Blackjack -> 0.0
            else -> 1.5 // wins against Stay
        }
    }
}
