package blackjack.model

class Hand(var cards: List<PlayingCard> = emptyList()) {
    val isBust: Boolean get() = calculateHand() > BUST_LIMIT
    val isBlackjack: Boolean get() =
        (cards.size == INIT_CARDS_SIZE) && (calculateHand() == BUST_LIMIT)

    init {
        if (cards.isEmpty()) {
            val deque = ArrayDeque(cards)
            repeat(INIT_CARDS_SIZE) {
                deque.addLast(PlayingCard.deck.giveCard())
            }
            cards = deque.toList()
        }
    }

    fun calculateHand(): Int {
        val values = cards.map { it.value }
        var aceCounts = values.count { it == BIG_ACE_VALUE }
        var score = values.sum()
        while (aceCounts > 0 && score > BLACKJACK_VALUE) {
            score -= ACE_REDUCE_VALUE
            aceCounts--
        }
        if (score < ACE_REDUCE_VALUE && values.contains(BIG_ACE_VALUE)) {
            score += ACE_REDUCE_VALUE
        }
        return score
    }

    companion object {
        const val INIT_CARDS_SIZE = 2
        const val BUST_LIMIT = 21
        const val BIG_ACE_VALUE = 11
        const val BLACKJACK_VALUE = 21
        const val ACE_REDUCE_VALUE = 10
    }
}
