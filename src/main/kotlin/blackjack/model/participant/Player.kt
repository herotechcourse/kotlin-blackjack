package blackjack.model.participant

import blackjack.model.Chips
import blackjack.model.card.Card
import blackjack.model.result.PlayerResultTracker
import blackjack.model.result.Result

class Player(name: String) : Participant(name, PlayerResultTracker()) {
    var bet: Chips = Chips.zero()
        private set

    val result: String
        get() = resultTracker.toString()
    val hand: List<Card>
        get() = participantHand.cards.toList()

    fun placeBet(amount: Double) {
        bet = Chips(amount)
    }

    fun addProfit() {
        profit =
            when (resultTracker.result) {
                Result.BLACKJACK -> bet.blackjack()
                Result.WIN -> bet
                Result.LOSE -> bet.lose()
                else -> Chips.zero()
            }
    }
}
