package blackjack.states

import blackjack.model.Card
import blackjack.model.Hand

interface State {
    val hand: Hand

    fun draw(card: Card): State

    fun stay(): State
}
