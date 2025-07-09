package blackjack.model

interface Playable {
    val name: String
    val hand: List<Card>

    fun requestCard(): Boolean
    fun drawCard(newCard: Card)
    fun calculateHand(): Int
    fun isBust(): Boolean
}
