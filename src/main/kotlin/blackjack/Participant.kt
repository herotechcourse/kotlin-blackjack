package blackjack

abstract class Participant(val name: String) {
    private val hand: MutableList<Card> = mutableListOf()

    fun getHand(): List<Card> = hand

    fun displayHand(): String {
        return hand.joinToString(", ") { it.display() }
    }

    fun addCard(card: Card) {
        hand.add(card)
    }
}