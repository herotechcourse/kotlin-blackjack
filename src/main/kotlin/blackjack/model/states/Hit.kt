package blackjack.model.states

import blackjack.model.cards.Card
import blackjack.model.cards.HandCards

class Hit(override val hand: HandCards) : Running {
    init {
        if (hand.total() > 21 || hand.size() < 2) {
            throw IllegalStateException()
        }
    }

    override fun draw(card: Card): State {
        val newHand = hand + card
        if (newHand.total() <= 21) {
            return Hit(newHand)
        }
        return Bust(newHand)
    }

    override fun stay(): State {
        return Stay(hand)
    }
}
