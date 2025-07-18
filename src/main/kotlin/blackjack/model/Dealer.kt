package blackjack.model

class Dealer : Participant("Dealer") {
    fun playTurn(deck: CardDeck) {
        while (state.isRunning() && state.hand.calculatePoints() < SEVENTEEN) {
            draw(deck.drawCard())
        }
        if (state.isRunning()) stay()
    }

    companion object {
        const val SEVENTEEN = 17
    }
}
