package blackjack.model

data class Player(
    override val name: String,
    override var bet: Int = Playable.INITIAL_BETTING_AMOUNT,
    override val hand: Hand = Hand(),
) : Playable {
    override var result = Result.NONE

    override fun drawCard(newCard: PlayingCard) {
        val deque = ArrayDeque(hand.cards)
        deque.addLast(newCard)
        hand.cards = deque.toList()
    }
}
