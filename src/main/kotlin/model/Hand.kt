package model

class Hand {
    private val cards: MutableSet<Card> = mutableSetOf()

    fun getCards(): Set<Card> = cards.toSet()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun scoreOnHand(): Int {
        return getScore(cards)
    }

    fun getScore(cards: Set<Card>): Int {
        var scoreTotal = scoreSum(cards)
        var aceCounter = aceCount(cards)
        while (scoreTotal > 21 && hasAce(aceCounter)) {
            scoreTotal -= 10
            aceCounter--
        }
        return scoreTotal
    }

    private fun hasAce(aceCounter: Int): Boolean = aceCounter != 0

    private fun scoreSum(cards: Set<Card>) = cards.sumOf { card -> Rank.score(card.rank) }

    private fun aceCount(cards: Set<Card>) = cards.count { it.rank == Rank.ACE }
}
