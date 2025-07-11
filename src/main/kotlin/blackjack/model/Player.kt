package blackjack.model

class Player(name: String) {
    private val person = Person(name)
    private val hand = Hand()
    val name = person.name

    val cards
        get() = hand.cards

    val isBlackJack
        get() = numberInHand() == 2 && calculatePoints() == 21
    val isBust
        get() = calculatePoints() > 21

    fun addCard(card: Card) = hand.addCard(card)

    fun numberInHand() = hand.numberOfCards()

    fun calculatePoints() = hand.calculatePoints()
}
