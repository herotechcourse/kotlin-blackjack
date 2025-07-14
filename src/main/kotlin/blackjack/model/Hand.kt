package blackjack.model

class Hand(val cards: List<Card>) {
    val size get() = cards.size

    constructor(vararg cards: Card) : this(cards.toList())

    operator fun plus(card: Card): Hand = Hand(cards + card)

    fun getScore(): Int {
        val hasAce = cards.any { it.isAce }
        var totalScore = cards.sumOf { it.score }

        if (hasAce && totalScore <= 11) {
            totalScore += 10
        }
        return totalScore
    }

    override fun toString(): String {
        return cards.joinToString(", ")
    }

    companion object {
        const val BLACK_JACK = 21
    }
}
