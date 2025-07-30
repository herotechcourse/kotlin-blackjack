package blackjack.model.states

import blackjack.model.cards.Card
import blackjack.model.cards.HandCards

class Init(override val hand: HandCards = HandCards()) : Running {
    init {
        if (hand.cards.size > 1) {
            throw IllegalStateException()
        }
    }

    override fun draw(card: Card): State {
        val newHand: HandCards = hand + card
        return when {
            newHand.cards.size == 1 -> Init(newHand)
            newHand.cards.size == 2 && newHand.total() == 21 -> BlackJack(newHand)
            else -> Hit(newHand)
        }
    }

    override fun stay(): State {
        throw IllegalStateException()
    }
}
