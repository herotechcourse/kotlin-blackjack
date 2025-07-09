package blackjack.model

class Player(
    override val name: String,
    override var isActive: Boolean = true,
    override val cardsInHand: MutableList<Card> = mutableListOf(),
) : Participant
