package blackjack.model

import blackjack.state.Running
import blackjack.state.Started

class Dealer(deck: Deck) : Participant("Dealer", deck) {
    override fun playTurn() {
        state =
            when (state) {
                is Running ->
                    if (shouldDraw()) {
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

    fun shouldDraw(): Boolean {
        return state is Running && state.hand.sumCards() < 17
    }
}
