package blackjack.states

import blackjack.model.Card
import blackjack.model.Hand

class Hit(override val hand: Hand) : Running {
    override fun draw(card: Card): State {
        val hand = this.hand + card
        if (hand.calculatePoints() > BLACKJACK) return Bust(hand)
        return Hit(hand)
    }

    override fun stay(): State {
        return Stay(hand)
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
