package blackjack.model

class Player(override val name: String, override var isActive: Boolean = true) :
    Participant {
    override val cardsInHand: MutableList<Card> = mutableListOf()

    override fun drawCard(card: Card) {
        cardsInHand += card
    }

    private fun checkAces() = cardsInHand.count { card -> card.rank == Rank.ACE }

    override fun calculateTotalValueOfCards(): Int {
        var totalValueOfCards = cardsInHand.sumOf { card -> card.rank.value }
        var aceCounter = checkAces()
        while (totalValueOfCards > 21 && aceCounter > 0) {
            totalValueOfCards -= 10
            aceCounter--
        }
        return totalValueOfCards
    }

    override fun updateActiveStatus(totalValueOfCards: Int) {
        if (totalValueOfCards > 21) {
            isActive = false
        }
    }
}
