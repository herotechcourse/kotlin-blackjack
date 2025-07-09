package model

abstract class BasePlayer(val name: String) {
    private val hand = Hand()

    init {
        require(name.isNotEmpty() && name.isNotBlank()) { "Name must not be empty" }
    }

    fun getScore(): Int {
        return hand.scoreOnHand()
    }

    fun getCardsNumber(): Int {
        return hand.getCards().size
    }

    abstract fun makeDecision(): Boolean

    fun drawCard(card: Card) {
        hand.addCard(card)
    }
}
