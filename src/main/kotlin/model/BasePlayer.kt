package model

abstract class BasePlayer() {
    protected val hand = Hand()
    fun getScore(): Int = hand.scoreOnHand()

    fun getCardsNumber(): Int = hand.getCards().size

    fun showCards(): Set<Card> = hand.getCards()

    fun drawCard(card: Card) {
        hand.addCard(card)
    }
}
