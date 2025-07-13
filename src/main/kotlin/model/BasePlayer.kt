package model

abstract class BasePlayer(val name: String) {
    protected val hand = Hand()

    init {
        require(name.isNotEmpty() && name.isNotBlank()) { "Name must not be empty" }
    }

    fun getScore(): Int = hand.scoreOnHand()

    fun getCardsNumber(): Int = hand.getCards().size

    fun showCards(): Set<Card> = hand.getCards()

    fun drawCard(card: Card) {
        hand.addCard(card)
    }
}
