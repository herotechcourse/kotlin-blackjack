package blackjack.state

import blackjack.model.card.Card

abstract class Finished : State {
    abstract val rate: Double

    override fun draw(card: Card): State {
        throw IllegalStateException()
    }

    override fun stand(): State {
        throw IllegalStateException()
    }

    override fun profit(money: Int): Double {
        return money * rate
    }
}
