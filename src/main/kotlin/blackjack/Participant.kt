package blackjack

abstract class Participant(val name: String) {
    val hand: Hand = Hand()

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun sumCards(): Int {
        return hand.sumCards()
    }

    abstract fun shouldHit(): Boolean
}