package blackjack.model.states

import blackjack.model.Bet

interface Running : State {
    override fun profit(
        bet: Bet,
        dealerState: State,
    ): Double {
        throw IllegalStateException()
    }
}
