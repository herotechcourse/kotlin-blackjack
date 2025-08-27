package blackjack.model

import blackjack.state.Active
import blackjack.state.Running

class Dealer(deck: Deck) : Participant("Dealer", deck) {
    override fun playTurn() {
        state =
            when (state) {
                is Running ->
                    if (state.shouldDraw(DEALER_GOAL_NUMBER)) {
                        state.run()
                    } else {
                        state.stay()
                    }
                is Active -> {
                    state.run()
                }
                else -> state
            }
    }

    companion object {
        const val DEALER_GOAL_NUMBER = 17
    }
}
