package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

abstract class Active(override val hand: Hand, override val deck: Deck) : State {
    override fun stay(): State {
        return Stay(hand, deck)
    }

    override fun canTransitionToBlackjack(): Boolean {
        return hand.sumCards() == 21
    }
}
