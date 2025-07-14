package blackjack.states

import blackjack.model.Hand

class Blackjack(override val hand: Hand) : Finished {
    override val isBlackjack: Boolean
        get() = true

    override fun profit(
        state: State,
        betMoney: Int,
    ): Double {
        return if (state.isBlackjack) {
            betMoney * DRAW_TIE
        } else {
            betMoney * BLACKJACK_WIN
        }
    }

    companion object {
        private const val DRAW_TIE = 0.0
        private const val BLACKJACK_WIN = 1.5
    }
}
