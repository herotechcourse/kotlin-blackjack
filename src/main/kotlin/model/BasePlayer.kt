package model

abstract class BasePlayer(val name: String) {
    var earning = Earning(0)
    protected val hand = Hand()

    init {
        require(name.isNotEmpty() && name.isNotBlank()) { "Name must not be empty" }
    }

    fun init(deck: Deck) {
        repeat(2) {
            drawCard(deck.pop())
        }
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
        doAfter: (BasePlayer) -> Unit,
        decision: (BasePlayer) -> Boolean,
    )
}
