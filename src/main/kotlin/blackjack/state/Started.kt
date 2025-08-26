package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

open class Started(override val hand: Hand, override val deck: Deck) : State {
    override fun run(): State {
        hand.addCard(deck.drawCard())
        hand.addCard(deck.drawCard())

        return if (isBlackjack()) {
            Blackjack(hand, deck)
        } else {
            Running(hand, deck)
        }
    }

    override fun stay(): State {
        return Stay(hand, deck)
    }

    override fun finish(): State {
        return Finished(hand, deck)
    }
}
