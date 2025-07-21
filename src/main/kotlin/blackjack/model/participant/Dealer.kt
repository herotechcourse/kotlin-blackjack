package blackjack.model.participant

import blackjack.model.GameConstants.BLACKJACK_SCORE
import blackjack.model.GameConstants.DEALER_MUST_HIT_LIMIT
import blackjack.model.GameConstants.DEALER_NOT_BET_MONEY
import blackjack.model.GameConstants.FIRST_ROUND_HIT_COUNTS
import blackjack.model.state.State

class Dealer() : Participant("Dealer", DEALER_NOT_BET_MONEY) {
    override var mutableState: State
        get() = state
        set(_) {}

    override val state: State
        get() = calculateState()

    override fun isBust() = state == State.BUST && score > BLACKJACK_SCORE

    override fun isBlackjack() = state == State.BLACKJACK && score == BLACKJACK_SCORE

    private fun calculateState(): State {
        if (isFirstRound()) return initialStateOrBlackjack()
        return evaluateState()
    }

    private fun initialStateOrBlackjack(): State {
        if (score == BLACKJACK_SCORE) return State.BLACKJACK
        return evaluateState()
    }

    private fun evaluateState(): State {
        if (score > BLACKJACK_SCORE) return State.BUST
        return when (isAbleToDraw()) {
            true -> State.HIT
            false -> State.STAY
        }
    }

    private fun isFirstRound(): Boolean = cardsCount() == FIRST_ROUND_HIT_COUNTS

    private fun isAbleToDraw(): Boolean = score <= DEALER_MUST_HIT_LIMIT
}
