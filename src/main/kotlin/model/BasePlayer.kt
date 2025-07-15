package model

abstract class BasePlayer(val name: String) {
    protected val hand = Hand()

    init {
        require(name.isNotEmpty() && name.isNotBlank()) { "Name must not be empty" }
    }

    fun getScore(): Int {
        return hand.scoreOnHand()
    }

    fun getCardsNumber(): Int {
        return hand.getCards().size
    }

    fun showCards(): Set<Card> {
        return hand.getCards()
    }

    fun drawCard(card: Card) {
        hand.addCard(card)
    }

    abstract fun turn(
        deck: Deck,
        doAfter: (String) -> (Unit),
        decision: () -> Boolean,
    )
}
