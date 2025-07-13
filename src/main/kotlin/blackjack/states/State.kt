package blackjack.states

import blackjack.model.Hand

interface State {
    val hand: Hand
}
