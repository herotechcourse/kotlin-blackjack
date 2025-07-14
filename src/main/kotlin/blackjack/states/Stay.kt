package blackjack.states

import blackjack.model.Hand

class Stay(override val hand: Hand) : Finished {
    override fun profit(
        state: State,
        betMoney: Int,
    ): Double {
        return if (state.isBlackjack) {
            betMoney * BLACKJACK_LOSE
        } else if (state.hand.calculatePoints() == hand.calculatePoints()) {
            betMoney * DRAW_TIE
        } else if (state.hand.calculatePoints() > hand.calculatePoints()) {
            betMoney * LOSE_LOSS
        } else {
            betMoney * WIN_GAIN
        }
    }

    companion object {
        private const val WIN_GAIN = 1.0
        private const val LOSE_LOSS = -1.0
        private const val DRAW_TIE = 0.0
        private const val BLACKJACK_LOSE = -1.5
    }
}
