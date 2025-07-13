package blackjack.model

class Hand(val cards: List<Card>) {
    val size get() = cards.size

    operator fun plus(card: Card): Hand = Hand(this.cards + card)

    fun calculatePoints(): Int {
        val countOfAce = cards.filter { it.rank.value == 1 }.size
        var sum =
            cards.sumOf {
                it.rank.value
            }

        repeat(countOfAce) {
            if (sum + 10 <= BLACKJACK) sum += 10
        }
        return sum
    }

    companion object {
        private const val BLACKJACK = 21
    }
}
