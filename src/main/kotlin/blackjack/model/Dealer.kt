package blackjack.model

data class Dealer(override val name: String = "Dealer") : Playable {
    override var hand = Hand()
    override var result: Result = Result.NONE
    override var bet = Playable.INITIAL_BETTING_AMOUNT

    override fun drawCard(newCard: PlayingCard) {
        val deque = ArrayDeque(hand.cards)
        deque.addLast(newCard)
        hand = Hand(deque.toList())
    }

    override fun calculateHand(): Int {
        return hand.calculateHand()
    }

    fun shouldDrawCardOrNot(): Boolean {
        val score = calculateHand()
        return score < DRAW_LIMIT
    }

    companion object {
        const val DRAW_LIMIT = 17
    }
}
