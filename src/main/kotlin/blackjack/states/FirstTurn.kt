package blackjack.states
import blackjack.model.Card
import blackjack.model.Hand

class FirstTurn(override val hand: Hand = Hand(emptyList())) : State {
    fun draw(card: Card): State {
        val hand = this.hand + card
        return this
    }
}
