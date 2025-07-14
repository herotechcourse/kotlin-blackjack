package blackjack.model

class HandCards(val cards: MutableList<Card> = mutableListOf()) {
    val total: Int
        get() = calculateTotal()

    fun add(card: Card) {
        cards.add(card)
    }

    private fun calculateTotal(): Int {
        var total = 0
        for (card in cards) {
            total += card.rank.value
        }
        total -= discount(total)
        return total
    }

    private fun discount(total: Int): Int {
        var numberOfAces = 0
        var discount = 0
        for (card in cards) {
            if (card.rank == Rank.ACE) numberOfAces++
        }
        repeat(numberOfAces) {
            if (total - discount > Rules.BLACKJACK_TARGET) {
                discount += 10
            }
        }
        return discount
    }

    operator fun compareTo(other: HandCards): Int {
        return this.calculateTotal() - other.calculateTotal()
    }
}
