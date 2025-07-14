package blackjack.model

import blackjack.states.FirstTurn

class Player(name: String) : Participant(name, FirstTurn(Hand(emptyList()))) {
    private var bettingAmount: Int = 0

    fun draw(card: Card) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }

    fun setBettingAmount(bettingAmount: Int) {
        this.bettingAmount = bettingAmount
    }
}
