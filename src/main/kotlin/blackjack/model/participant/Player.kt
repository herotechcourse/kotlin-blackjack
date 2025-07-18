package blackjack.model.participant

import blackjack.model.GameConstants.BLACKJACK_SCORE
import blackjack.model.GameConstants.PLAYER_FIRST_HIT_COUNT
import blackjack.model.state.State

class Player(name: String, amount: Int = 0) : Participant(name, amount) {
    override var currentState: State = State.HIT
        get() =
            when {
                score > BLACKJACK_SCORE -> State.BUST
                isFirstRound() -> blackjackOrHit()
                else -> field
            }

    private fun isFirstRound(): Boolean {
        return cardsCount() == PLAYER_FIRST_HIT_COUNT
    }

    private fun blackjackOrHit(): State {
        return when (score) {
            BLACKJACK_SCORE -> State.BLACKJACK
            else -> State.HIT
        }
    }
}
