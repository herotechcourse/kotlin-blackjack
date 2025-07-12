package blackjack.model

class Hand() {
    private val hold: Cards = Cards(setOf())

    val cards
        get() = hold.cards

    fun numberOfCards() = hold.cards.size

    fun addCard(card: Card) = this.hold.add(card)

    fun calculatePoints(): Int {
        val countOfAce = hold.cards.filter { it.rank.value == 1 }.size
        var sum =
            hold.cards.sumOf {
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
