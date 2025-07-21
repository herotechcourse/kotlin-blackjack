package blackjack.model

class Hand(var cards: List<PlayingCard> = emptyList()) {
    fun initCards(): Hand {
        val deque = ArrayDeque(cards)
        repeat(2) {
            deque.addLast(PlayingCard.deck.giveCard())
        }
        cards = deque.toList()
        return this
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
        const val BIG_ACE_VALUE = 11
        const val BLACKJACK_VALUE = 21
        const val ACE_REDUCE_VALUE = 10
    }
}
