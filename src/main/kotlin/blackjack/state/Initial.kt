package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

class Initial(hand: Hand, deck: Deck) : Active(hand, deck) {
    override fun run(): State {
        hand.addCard(deck.drawCard())
        hand.addCard(deck.drawCard())

        return if (canTransitionToBlackjack()) {
            Blackjack(hand, deck)
        } else {
            Running(hand, deck)
        }
    }

    override fun stay(): State {
        return Stay(hand, deck)
    }
}
