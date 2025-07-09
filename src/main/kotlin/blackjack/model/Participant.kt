package blackjack.model

interface Participant {
    val name: String
    val isActive: Boolean
    val cardsInHand: MutableList<Card>
    val totalValueOfCards: Int

    fun drawCard(): Card

    fun updateActiveStatus()
}
