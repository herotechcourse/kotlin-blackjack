package model

abstract class BasePlayer(val name: String) {
    private val hand = Hand()

    fun getScore(): Int {
        return hand.scoreOnHand()
    }

    abstract fun makeDecision(): Boolean

    fun drawCard(card: Card) {
        hand.addCard(card)
    }
}
