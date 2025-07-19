package blackjack.model.participant

import blackjack.model.GameConstants
import blackjack.model.GameConstants.BLACKJACK_SCORE
import blackjack.model.GameConstants.FIRST_ROUND_HIT_COUNTS
import blackjack.model.state.State

class Dealer() : Participant("Dealer", DEALER_NOT_BET_MONEY) {
    override var state: State = State.HIT
        get() = calculateState()

    override fun isBust() = state == State.BUST

    override fun isBlackjack() = state == State.BLACKJACK

    private fun calculateState(): State {
        if (isFirstRound()) return blackOrHit()
        if (score > BLACKJACK_SCORE) return State.BUST

        return if (score <= GameConstants.DEALER_MUST_HIT_LIMIT) {
            State.HIT
        } else {
            State.STAY
        }
    }

    private fun isFirstRound(): Boolean {
        return cardsCount() == FIRST_ROUND_HIT_COUNTS
    }

    private fun blackOrHit(): State {
        return when (score) {
            BLACKJACK_SCORE -> State.BLACKJACK
            else -> State.HIT
        }
    }

    companion object {
        const val DEALER_NOT_BET_MONEY = 0
    }
}
