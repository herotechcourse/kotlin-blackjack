package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Hand
import blackjack.model.result.Result
import blackjack.model.result.ResultTracker

abstract class Participant(val name: String, protected val resultTracker: ResultTracker) {
    protected val hand = Hand()

    init {
        require(name.isNotBlank()) { "Wrong name: $name. Participant name should not be blank." }
    }

    fun addCard(newCard: Card) = hand.addCard(newCard)

    fun getScore(): Int = hand.getScore()

    fun hasBlackJack(): Boolean = hand.hasBlackJack()

    fun isBusts(): Boolean = hand.isBusts()

    fun recordResult(result: Result) = resultTracker.record(result)

    override fun toString(): String {
        return "$name's cards: $hand"
    }
}
