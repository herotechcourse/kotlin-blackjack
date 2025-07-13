package blackjack.states
import blackjack.model.Card
import blackjack.model.Hand

class FirstTurn(override val hand: Hand = Hand(emptyList())) : State {
    override fun draw(card: Card): State {
        val hand = this.hand + card
        if (hand.size == 2) {
            if (hand.calculatePoints() == 21) return Blackjack(hand)
            return Hit(hand)
        }
        return FirstTurn(hand)
    }
}
