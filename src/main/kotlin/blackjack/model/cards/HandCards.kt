package blackjack.model.cards

class HandCards(val cards: List<Card> = listOf()) {
    operator fun plus(card: Card): HandCards = HandCards(cards + card)

    fun total(): Int {
        var total = 0
        for (card in cards) {
            total += card.rank.value
        }
        total -= discount(total)
        return total
    }

    fun size() = cards.size

    private fun discount(total: Int): Int {
        var numberOfAces = 0
        var discount = 0
        for (card in cards) {
            if (card.rank == Rank.ACE) numberOfAces++
        }
        repeat(numberOfAces) {
            if (total - discount > 21) {
                discount += 10
            }
        }
        return discount
    }

    operator fun compareTo(other: HandCards): Int {
        return this.total() - other.total()
    }

    companion object {
        fun from(vararg cards: Card): HandCards {
            return HandCards(cards.toList())
        }
    }
}
