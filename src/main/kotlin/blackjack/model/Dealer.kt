package blackjack.model

import blackjack.states.FirstTurn
import blackjack.states.Running

class Dealer() : Participant("Dealer", FirstTurn(Hand(emptyList()))) {
    fun playTurn(deck: CardDeck) {
        while (state is Running && state.hand.calculatePoints() < SEVENTEEN) {
            state = state.draw(deck.drawCard())
        }
        if (state is Running) {
            state = state.stay()
        }
    }

    companion object {
        const val SEVENTEEN = 17
    }
}
