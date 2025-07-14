package blackjack.model

import blackjack.states.FirstTurn

class Dealer() : Participant("Dealer", FirstTurn(Hand(emptyList()))) {
    fun hasToPlay(): Boolean {
        return (state.hand.calculatePoints() < 17)
    }
}
