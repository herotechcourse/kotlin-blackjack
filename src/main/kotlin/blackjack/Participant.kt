package blackjack

abstract class Participant(val name: String) {
    val hand: Hand = Hand()

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun sumCards(): Int {
        return hand.sumCards()
    }

    fun getResult(): ResultTypes {
        val result = sumCards()
        return when {
            result == 21 -> ResultTypes.BLACKJACK
            result > 21 -> ResultTypes.BUSTED
            else -> ResultTypes.STAY
        }
    }

    abstract fun shouldHit(): Boolean



}