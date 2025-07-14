package blackjack.model

import blackjack.states.FirstTurn

class Player(name: String) : Participant(name, FirstTurn(Hand(emptyList()))) {
    fun draw(card: Card) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }
}
