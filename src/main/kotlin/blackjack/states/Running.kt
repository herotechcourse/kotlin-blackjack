package blackjack.states

import blackjack.view.Errors

interface Running : State {
    override fun isRunning(): Boolean {
        return true
    }

    override fun profit(
        state: State,
        betMoney: Int,
    ): Double {
        throw IllegalStateException(Errors.INVALID_PROFIT.message)
    }
}
