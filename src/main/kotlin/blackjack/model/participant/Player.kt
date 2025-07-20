package blackjack.model.participant

import blackjack.model.GameConstants.BLACKJACK_SCORE
import blackjack.model.GameConstants.FIRST_ROUND_HIT_COUNTS
import blackjack.model.state.State

class Player(name: String, amount: Int = 0) : Participant(name, amount) {
    override var mutableState: State = State.HIT
    override val state: State
        get() =
            when {
                score > BLACKJACK_SCORE -> State.BUST
                isFirstRound() -> blackjackOrHit()
                else -> mutableState
            }

    override fun isBust() = state == State.BUST

    override fun isBlackjack() = state == State.BLACKJACK

    private fun isFirstRound(): Boolean {
        return cardsCount() == FIRST_ROUND_HIT_COUNTS
    }

    private fun blackjackOrHit(): State {
        return when (score) {
            BLACKJACK_SCORE -> State.BLACKJACK
            else -> State.HIT
        }
    }
}
