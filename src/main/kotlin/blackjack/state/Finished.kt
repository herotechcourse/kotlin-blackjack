package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

open class Finished(override val hand: Hand, override val deck: Deck) : State {
    override fun run(): State {
        return this
    }

    override fun stay(): State {
        return this
    }

    override fun finish(): State {
        return this
    }
}
