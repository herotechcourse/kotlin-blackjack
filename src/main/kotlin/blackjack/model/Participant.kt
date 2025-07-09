package blackjack.model

interface Participant {
    val name: String
    val isActive: Boolean
    val cardsInHand: MutableList<Card>

    fun drawCard(card: Card)

    fun calculateTotalValueOfCards(): Int

    fun updateActiveStatus(totalValueOfCards: Int)
}
