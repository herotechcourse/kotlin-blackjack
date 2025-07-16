package blackjack.model

import blackjack.states.FirstTurn

class Dealer() : Participant("Dealer", FirstTurn(Hand(emptyList()))) {
    fun playTurn(deck: CardDeck) {
        while (state.isRunning() && state.hand.calculatePoints() < SEVENTEEN) {
            draw(deck.drawCard())
        }
        stay()
    }

    companion object {
        const val SEVENTEEN = 17
    }
}
