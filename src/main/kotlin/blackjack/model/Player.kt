package blackjack.model

data class Player(override val name: String) : Playable {
    override var hand = Hand()
    override var result: Result = Result.NONE

    override fun drawCard(newCard: PlayingCard) {
        val deque = ArrayDeque(hand.cards)
        deque.addLast(newCard)
        hand = Hand(deque.toList())
    }

    override fun calculateHand(): Int {
        return hand.calculateHand()
    }
}
