package blackjack.model

class Player(name: String) {
    private val person = Person(name)
    private val hand = Hand()

    fun addCard(card: Card) = hand.addCard(card)

    fun numberInHand() = hand.numberOfCards()

    fun calculatePoints() = hand.calculatePoints()

    val name = person.name

    val cards = hand.cards
}
