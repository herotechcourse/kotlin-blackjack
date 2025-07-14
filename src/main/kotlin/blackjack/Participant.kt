package blackjack

abstract class Participant(val name: String) {
    private val hand = Hand()

    fun addCard(card: Card) {
        hand.add(card)
    }

    fun getScore() = hand.getScore()

    fun isBusted() = hand.isBusted()

    fun getNumberOfCardsInHand() = hand.getNumberOfCards()

    fun displayHand() = hand.display()

    fun getFirstTwoCards() = hand.firstTwoCards()
}
