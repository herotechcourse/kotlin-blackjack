package blackjack.model

data class Dealer(override val name: String = "Dealer") : Playable {
    private var _hand = Hand()
    override val hand: Hand
        get() = _hand

    override fun drawCard(newCard: Card) {
        val deque = ArrayDeque(hand.cards)
        deque.addLast(newCard)
        _hand = Hand(deque.toList())
    }

    override fun calculateHand(): Int {
        return _hand.calculateCards()
    }

    fun shouldDrawCardOrNot(): Boolean {
        val score = calculateHand()
        return score <= 16
    }
}
