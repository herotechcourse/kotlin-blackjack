package blackjack.model

data class Player(override val name: String) : Playable {
    override var hand = Hand()
    override var result = Result.NONE
    override var bet = Playable.INITIAL_BETTING_AMOUNT

    override fun drawCard(newCard: PlayingCard) {
        val deque = ArrayDeque(hand.cards)
        deque.addLast(newCard)
        hand = Hand(deque.toList())
    }

    override fun calculateHand(): Int {
        return hand.calculateHand()
    }

    fun initHand(): Player {
        hand = Hand().initCards()
        return this
    }

    fun placeBets(amount: Int): Player {
        bet += amount
        return this
    }
}
