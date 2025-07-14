package blackjack.model

class Hand {
    private val cards: MutableList<Card> = mutableListOf()

    fun add(card: Card) {
        cards.add(card)
    }

    fun getNumberOfCards() = cards.size

    fun getScore(): Int {
        var total = cards.sumOf { it.getScore() }
        var aces = cards.count { it.isAce() }

        while (total > 21 && aces > 0) {
            total -= 10
            aces--
        }

        return total
    }

    fun isBusted(): Boolean = getScore() > 21

    fun firstTwoCards(): List<Card> = cards.take(2)

    fun display(): String = cards.joinToString(", ") { it.display() }
}
