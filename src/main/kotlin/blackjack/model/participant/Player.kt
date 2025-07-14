package blackjack.model.participant

import blackjack.model.Money
import blackjack.model.card.Card
import blackjack.model.result.PlayerResultTracker

class Player(name: String) : Participant(name, PlayerResultTracker()) {
    var bet: Money = Money.zero()
        private set

    val result: String
        get() = resultTracker.toString()
    val hand: List<Card>
        get() = _hand.cards

    fun placeBet(amount: Double) {
        bet = Money(amount)
    }
}
