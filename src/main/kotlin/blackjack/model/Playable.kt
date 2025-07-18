package blackjack.model

interface Playable {
    val name: String
    var hand: Hand

    fun requestCard(condition: () -> Boolean): Boolean {
        return condition()
    }

    fun drawCard(newCard: PlayingCard)

    fun calculateHand(): Int

    fun isBust(): Boolean {
        return calculateHand() > BUST_LIMIT
    }

    companion object {
        const val BUST_LIMIT = 21
    }
}
