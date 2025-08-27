package blackjack.model

import blackjack.state.Running
import blackjack.state.Started

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
                is Started -> {
                    state.run()
                }
                else -> state
            }
    }

    companion object {
        const val DEALER_GOAL_NUMBER = 17
    }
}
