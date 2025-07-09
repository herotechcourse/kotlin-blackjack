package blackjack.model

interface Playable {
    val name: String
    val hand: List<Card>

    fun requestCard(condition: () -> Boolean): Boolean

    fun drawCard(newCard: Card)

    fun calculateHand(): Int

    fun isBust(): Boolean
}
