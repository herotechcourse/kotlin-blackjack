package blackjack.model

data class Player(override val name: String) : Playable {
    private var _hand = emptyList<Card>()
    override val hand: List<Card> get() = _hand

    override fun requestCard(condition: () -> Boolean): Boolean {
        return condition()
    }

    override fun drawCard(newCard: Card) {
        val deque = ArrayDeque(hand)
        deque.addLast(newCard)
        _hand = deque.toList()
    }

    // TODO: MUST have to find a solid logic for calculation
    override fun calculateHand(): Int {
        val values = _hand.map { Rank.of(it.digit).value }
        var aceCounts = values.count { it == 11 }
        val preScore = values.sum()
        var score = preScore
        while (aceCounts > 0 && score > 21) {
            score -= 10
            aceCounts--
        }
        if (score < 10 && values.contains(11)) {
            score += 10
        }
        return score
    }

    override fun isBust(): Boolean {
        return calculateHand() > 21
    }

    fun getStringOfHand(): String {
        val names = _hand.map { it.name }
        return names.joinToString(", ")
    }
}
