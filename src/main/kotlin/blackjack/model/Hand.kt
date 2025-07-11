package blackjack.model

class Hand(private val dealtCards: MutableList<Card> = mutableListOf()) {
    val cards: List<Card>
        get() = dealtCards.toList()

    fun addCard(card: Card) {
        if (!isBusts()) dealtCards.add(card)
    }

    fun hasBlackJack(): Boolean = dealtCards.isNotEmpty() && getScore() == BLACK_JACK

    fun isBusts(): Boolean = dealtCards.isNotEmpty() && getScore() > BLACK_JACK

    fun getScore(): Int {
        var aceCount = 0
        var score = 0
        dealtCards.forEach {
            if (it.rank == Card.Rank.ACE) ++aceCount

            score += it.rank.value
            if (score > BLACK_JACK && aceCount > 0) {
                score -= ACE_ADJUSTMENT
                --aceCount
            }
        }

        return score
    }

    override fun toString(): String {
        return dealtCards.joinToString(", ")
    }

    companion object {
        const val BLACK_JACK = 21
        const val ACE_ADJUSTMENT = 10
    }
}
