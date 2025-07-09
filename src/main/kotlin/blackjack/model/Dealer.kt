package blackjack.model

class Dealer(
    override val name: String = "Dealer",
    override var isActive: Boolean = true,
    override val cardsInHand: MutableList<Card> = mutableListOf(),
) : Participant {
    fun mustDraw(totalValueOfCards: Int) = totalValueOfCards <= 16
}
