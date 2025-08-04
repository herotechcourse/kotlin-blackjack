package blackjack.model

interface Playable {
    val name: String
    val hand: Hand
    var result: Result
    val bet: Int

    fun requestCard(condition: () -> Boolean): Boolean {
        return condition()
    }

    fun drawCard(newCard: PlayingCard)

    companion object {
        const val INITIAL_BETTING_AMOUNT = 0
    }
}
