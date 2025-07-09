package blackjack.model

abstract class Participant(val name: String) {
    private val hand = Hand()

    init {
        require(name.isNotBlank()) { "Wrong name: $name. Participant name should not be blank." }
    }

    fun isBlackjack(): Boolean = hand.isBlackjack()

    fun isBusts(): Boolean = hand.isBusts()

    fun addCard(newCard: Card) = hand.addCard(newCard)

    override fun toString(): String {
        return "$name's cards: ${hand}"
    }
}