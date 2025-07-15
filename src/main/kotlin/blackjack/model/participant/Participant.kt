package blackjack.model.participant

import blackjack.model.Chips
import blackjack.model.card.Card
import blackjack.model.card.Hand
import blackjack.model.result.Result
import blackjack.model.result.ResultTracker

abstract class Participant(val name: String) {
    @Suppress("PropertyName")
    protected val participantHand = Hand()
    var profit: Chips = Chips.zero
        protected set

    init {
        require(name.isNotBlank()) { "Wrong name: $name. Participant name should not be blank." }
    }

    fun addCard(newCard: Card) = participantHand.addCard(newCard)

    fun getScore(): Int = participantHand.getScore()

    fun hasBlackJack(): Boolean = participantHand.hasBlackJack()

    fun isBusts(): Boolean = participantHand.isBusts()

    fun sortHand() {
        participantHand.sortCards()
    }
}
