package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Hand
import blackjack.model.result.Result
import blackjack.model.result.ResultTracker

abstract class Participant(val name: String, protected val resultTracker: ResultTracker) {
    @Suppress("PropertyName")
    protected val _hand = Hand()

    init {
        require(name.isNotBlank()) { "Wrong name: $name. Participant name should not be blank." }
    }

    fun addCard(newCard: Card) = _hand.addCard(newCard)

    fun getScore(): Int = _hand.getScore()

    fun hasBlackJack(): Boolean = _hand.hasBlackJack()

    fun isBusts(): Boolean = _hand.isBusts()

    fun recordResult(result: Result) = resultTracker.record(result)

    override fun toString(): String {
        return name
    }
}
