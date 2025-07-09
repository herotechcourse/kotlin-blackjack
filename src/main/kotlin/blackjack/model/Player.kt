package blackjack.model

class Player(
    private val person: Person,
    private val hand: Hand,
) {
    constructor(name: String) : this(Person(name), Hand())

    fun addCard(card: Card) = hand.addCard(card)

    fun numberInHand() = hand.numberOfCards()
}
