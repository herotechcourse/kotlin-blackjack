package blackjack.model.states

import blackjack.model.cards.Card

abstract class Stopped : State {
    abstract val rate: Double

    override fun draw(card: Card): State {
        throw IllegalStateException()
    }

    override fun stay(): State {
        throw IllegalStateException()
    }

    companion object {
        const val DRAW = 0.0
        const val LOSS = -1.0
    }
}
