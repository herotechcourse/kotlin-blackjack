package blackjack.states

import blackjack.model.Card
import blackjack.view.Errors

interface Finished : State {
    override fun draw(card: Card): State {
        throw IllegalStateException(Errors.INVALID_DRAW.message)
    }

    override fun stay(): State {
        throw IllegalStateException(Errors.INVALID_STAY.message)
    }

    override fun isRunning(): Boolean {
        return false
    }
}
