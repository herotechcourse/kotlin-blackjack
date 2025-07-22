package blackjack.model

data class Player(override val name: String) : Playable {
    override var hand = Hand().initCards()
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

    fun placeBets(bettingPrompt: () -> Int): Player {
        val amount = bettingPrompt()
        bet += amount
        return this
    }
}
