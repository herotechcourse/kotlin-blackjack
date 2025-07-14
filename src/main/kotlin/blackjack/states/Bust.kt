package blackjack.states

import blackjack.model.Hand

class Bust(override val hand: Hand) : Finished {
    override fun profit(
        state: State,
        betMoney: Int,
    ): Double {
        return betMoney * LOSE_LOSS
    }

    companion object {
        private const val LOSE_LOSS = -1.0
    }
}
