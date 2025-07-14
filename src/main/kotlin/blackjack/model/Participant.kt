package blackjack.model

abstract class Participant(open val name: String) {
    val cardsInHand: MutableList<Card> = mutableListOf()

    fun drawCard(cards: List<Card>) {
        cardsInHand.addAll(cards)
    }

    private fun checkAces() = cardsInHand.count { it.rank == Rank.ACE }

    private fun calculateTotalValueOfCards(): Int {
        var total = cardsInHand.sumOf { it.rank.value }
        var aceCounter = checkAces()
        while (total > BUST_LIMIT && aceCounter > 0) {
            total -= 10
            aceCounter--
        }
        return total
    }

    fun total(): Int = calculateTotalValueOfCards()

    fun isBusted(): Boolean = total() > BUST_LIMIT

    fun isStillInGame(): Boolean = !isBusted()

    companion object {
        const val BUST_LIMIT = 21
    }
}
