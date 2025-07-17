package blackjack.states
import blackjack.model.Card
import blackjack.model.Hand
import blackjack.view.Errors

class FirstTurn(override val hand: Hand = Hand(emptyList())) : Running {
    override fun draw(card: Card): State {
        val hand = this.hand + card
        if (hand.size == TWO_CARDS) {
            if (hand.calculatePoints() == BLACKJACK) return Blackjack(hand)
            return Hit(hand)
        }
        return FirstTurn(hand)
    }

    override fun stay(): State {
        throw IllegalStateException(Errors.INVALID_STAY.message)
    }

    override fun isFirstTurn(): Boolean {
        return true
    }

    companion object {
        const val BLACKJACK = 21
        const val TWO_CARDS = 2
    }
}
