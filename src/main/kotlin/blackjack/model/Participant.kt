package blackjack.model

abstract class Participant(
    val name: String,
    var isActive: Boolean = true,
    val cardsInHand: MutableList<Card> = mutableListOf()
) {

    fun drawCard(card: Card) {
        cardsInHand += card
    }

    private fun checkAces() = cardsInHand.count { card -> card.rank == Rank.ACE }

    fun calculateTotalValueOfCards(): Int {
        var totalValueOfCards = cardsInHand.sumOf { card -> card.rank.value }
        var aceCounter = checkAces()
        while (totalValueOfCards > 21 && aceCounter > 0) {
            totalValueOfCards -= 10
            aceCounter--
        }
        return totalValueOfCards
    }

    fun updateActiveStatus(totalValueOfCards: Int) {
        if (totalValueOfCards > 21) {
            isActive = false
        }
    }

}
