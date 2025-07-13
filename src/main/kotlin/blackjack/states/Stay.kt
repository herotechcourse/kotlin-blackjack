package blackjack.states

import blackjack.model.Card
import blackjack.model.Hand
import blackjack.view.Errors

class Stay(override val hand: Hand) : State {
    override fun draw(card: Card): State {
        throw IllegalStateException(Errors.INVALID_DRAW.message)
    }
}
