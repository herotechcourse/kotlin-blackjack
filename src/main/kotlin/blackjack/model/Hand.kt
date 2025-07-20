package blackjack.model

class Hand(val cards: List<Card> = emptyList()) {
    val size get() = cards.size

    operator fun plus(card: Card): Hand = Hand(cards + card)

    fun calculateSum(): Int {
        val countOfAce = cards.count { it.rank == Rank.ACE }
        var sum = cards.sumOf { it.rank.value }

        repeat(countOfAce) {
            if (sum + 10 <= BLACK_JACK) sum += 10
        }
        return sum
    }

    companion object {
        const val BLACK_JACK = 21
    }
}
