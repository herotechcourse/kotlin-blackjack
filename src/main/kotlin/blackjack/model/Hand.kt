package blackjack.model

class Hand {
    val dealtCards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        if (!isBusts()) dealtCards.add(card)
    }

    fun hasBlackJack(): Boolean = dealtCards.isNotEmpty() && getScore() == BLACK_JACK

    fun isBusts(): Boolean = dealtCards.isNotEmpty() && getScore() > BLACK_JACK

    fun getScore(): Int {
        var aceCount = 0
        var score = 0
        dealtCards.forEach {
            if (it.rank == Card.Rank.ACE) aceCount += 1

            score += it.rank.value
            if (score > BLACK_JACK && aceCount > 0) {
                score -= 10
                aceCount -= 1
            }
        }

        return score
    }

    override fun toString(): String {
        return dealtCards.joinToString(", ")
    }

    companion object {
        const val BLACK_JACK = 21
    }
}
