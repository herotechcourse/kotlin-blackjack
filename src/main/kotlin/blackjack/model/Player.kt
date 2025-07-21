package blackjack.model

data class Player(override val name: String, override var hand: Hand = Hand()) : Playable {
    override var result = Result.NONE

    override fun drawCard(newCard: PlayingCard) {
        val deque = ArrayDeque(hand.cards)
        deque.addLast(newCard)
        hand = Hand(deque.toList())
    }

    override fun calculateHand(): Int {
        return hand.calculateHand()
    }
}
