package blackjack.model

data class Player(override val name: String) : Playable {
    private var _hand = Hand()
    override val hand: Hand
        get() = _hand

    override fun drawCard(newCard: PlayingCard) {
        val deque = ArrayDeque(hand.cards)
        deque.addLast(newCard)
        _hand = Hand(deque.toList())
    }

    override fun calculateHand(): Int {
        return _hand.calculateHand()
    }
}
