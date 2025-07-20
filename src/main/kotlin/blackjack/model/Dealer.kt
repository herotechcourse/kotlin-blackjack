package blackjack.model

class Dealer() : Participant("Dealer") {
    var finalEarnings: Double = 0.0

    fun playTurn(deck: Deck) {
        while (handState.isRunning() && handState.hand.calculateSum() <= DEALER_STAND) {
            draw(deck.drawCard())
        }
        if (handState.isRunning()) stay()
    }

    companion object {
        const val DEALER_STAND = 16
    }
}
