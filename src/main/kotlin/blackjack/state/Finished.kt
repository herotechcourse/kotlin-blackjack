package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

abstract class Finished(
    override val hand: Hand,
    override val deck: Deck,
) : State {
    abstract fun earningsAgainst(other: Finished): Double

    override fun run() = this

    override fun stay() = this
}
