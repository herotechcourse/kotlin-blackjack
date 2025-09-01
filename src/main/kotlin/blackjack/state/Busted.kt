package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

class Busted(hand: Hand, deck: Deck) : Finished(hand, deck) {
    override fun earningsAgainst(other: Finished): Double = -1.0
}
