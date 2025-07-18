package blackjack.model.participant

import blackjack.model.GameConstants
import blackjack.model.GameConstants.BLACKJACK_SCORE
import blackjack.model.GameConstants.PLAYER_FIRST_HIT_COUNT
import blackjack.model.state.State

class Dealer() : Participant("Dealer", NO_BET_MONEY) {
    override var currentState: State = State.HIT
        get() = calculateState()

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
        return cardsCount() == PLAYER_FIRST_HIT_COUNT
    }

    private fun blackOrHit(): State {
        return when (score) {
            BLACKJACK_SCORE -> State.BLACKJACK
            else -> State.HIT
        }
    }

    companion object {
        const val NO_BET_MONEY = 0
    }
}
