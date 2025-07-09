package blackjack.model

class Dealer(name: String): Participant(name) {
    val deck = Deck()

    fun dealCard(): Card {
        return deck.drawCard()
    }

    fun shouldStand(): Boolean = hand.getScore() > DEALER_STAND

    companion object {
        const val DEALER_STAND = 16
    }
}
