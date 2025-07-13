package blackjack.model.participant

import blackjack.model.GameConstants.BLACKJACK_SCORE
import blackjack.model.GameConstants.PLAYER_FIRST_HIT_COUNT
import blackjack.model.state.State

class Player(name: String) : Participant(name) {
    override var _state: State = State.HIT
        get() = evaluateState()

    private fun evaluateState(): State {
        if (isFirstRound()) return blackOrHit()
        if (points > BLACKJACK_SCORE) return State.BUST
        return _state
    }

    private fun isFirstRound(): Boolean {
        return cardsCount() == PLAYER_FIRST_HIT_COUNT
    }

    private fun blackOrHit(): State {
        return when (points) {
            BLACKJACK_SCORE -> State.BLACKJACK
            else -> State.HIT
        }
    }
}