package blackjack.model

class Player(name: String) {
    private val playerName = PlayerName(name)
    private val hand = Hand()
    val name = playerName.name

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
