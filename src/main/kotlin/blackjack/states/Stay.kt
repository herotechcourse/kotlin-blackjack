package blackjack.states

import blackjack.model.Card
import blackjack.model.Hand

class Stay(override val hand: Hand) : State {
    override fun draw(card: Card): State {
        TODO("Not yet implemented")
    }
}
