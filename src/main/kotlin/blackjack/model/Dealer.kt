package blackjack.model

data class Dealer(
    override val name: String = "Dealer",
    override val hand: Hand = Hand(),
) : Playable {
    override var result: Result = Result.NONE
    override val bet = Playable.INITIAL_BETTING_AMOUNT

    override fun drawCard(newCard: PlayingCard) {
        val deque = ArrayDeque(hand.cards)
        deque.addLast(newCard)
        hand.cards = deque.toList()
    }

    fun shouldDrawCardOrNot(): Boolean {
        val score = hand.calculateHand()
        return score < DRAW_LIMIT
    }

    companion object {
        const val DRAW_LIMIT = 17
    }
}
