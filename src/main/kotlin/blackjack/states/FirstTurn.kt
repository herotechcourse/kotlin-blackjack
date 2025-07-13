package blackjack.states
import blackjack.model.Card
import blackjack.model.Hand
import blackjack.view.Errors

class FirstTurn(override val hand: Hand = Hand(emptyList())) : State {
    override fun draw(card: Card): State {
        val hand = this.hand + card
        if (hand.size == 2) {
            if (hand.calculatePoints() == 21) return Blackjack(hand)
            return Hit(hand)
        }
        return FirstTurn(hand)
    }

    override fun stay(): State {
        throw IllegalStateException(Errors.INVALID_STAY.message)
    }
}
