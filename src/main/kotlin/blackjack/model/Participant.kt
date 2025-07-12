package blackjack.model

abstract class Participant(
    val name: String,
    var isBusted: Boolean = false,
    val cardsInHand: MutableList<Card> = mutableListOf()
) {

    fun drawCard(card: Card) {
        cardsInHand += card
    }

    private fun checkAces() = cardsInHand.count { card -> card.rank == Rank.ACE }

    fun calculateTotalValueOfCards(): Int {
        var totalValueOfCards = cardsInHand.sumOf { card -> card.rank.value }
        var aceCounter = checkAces()
        while (totalValueOfCards > BlackJackValues.HAND_VALUE_LIMIT && aceCounter > 0) {
            totalValueOfCards -= BlackJackValues.ACE_DIFFERENCE
            aceCounter--
        }
        return totalValueOfCards
    }

    fun updateBustedStatus(totalValueOfCards: Int) {
        if (totalValueOfCards > BlackJackValues.HAND_VALUE_LIMIT) {
            isBusted = true
        }
    }
}
