package model

abstract class BasePlayer() {
    protected val hand = Hand()
    var earnings : Double = 0.0

    fun getScore(): Int = hand.scoreOnHand()

    fun showCards(): Set<Card> = hand.getCards()

    fun drawCard(card: Card) {
        hand.addCard(card)
    }

    fun isBlackJack() : Boolean = getScore() == 21 && showCards().size == 2
}
