package blackjack.model

interface Playable {
    val name: String
    val hand: Hand

    fun requestCard(condition: () -> Boolean): Boolean {
        return condition()
    }

    fun drawCard(newCard: Card)

    fun calculateHand(): Int

    fun isBust(): Boolean {
        return calculateHand() > 21
    }
}
