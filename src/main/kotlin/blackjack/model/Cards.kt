package blackjack.model

class Cards(val cards: MutableList<Card>) {

    private fun checkAces() = cards.count { card -> card.rank == Rank.ACE }

    fun addCard(card: Card) {
        cards += card
    }

    fun calculateTotalValueOfCards(): Int {
        var totalValueOfCards = cards.sumOf { card -> card.rank.value }
        var aceCounter = checkAces()
        while (totalValueOfCards > BlackJackValues.HAND_VALUE_LIMIT && aceCounter > 0) {
            totalValueOfCards -= BlackJackValues.ACE_DIFFERENCE
            aceCounter--
        }
        return totalValueOfCards
    }

    fun checkIfIsBustHand() = calculateTotalValueOfCards() > BlackJackValues.HAND_VALUE_LIMIT
}
