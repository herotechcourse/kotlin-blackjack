package blackjack.model

abstract class Participant(open val name: String) {
    private val hand = Hand()

    fun addCard(card: Card) {
        hand.add(card)
    }

    open fun getScore() = hand.getScore()

    open fun isBusted() = hand.isBusted()

    open fun isBlackJack() = hand.isBlackjack(getScore())

    fun getNumberOfCardsInHand() = hand.getNumberOfCards()

    fun displayHand() = hand.display()

    fun getFirstTwoCards() = hand.firstTwoCards()
}
