package blackjack.model

import blackjack.state.Started
import blackjack.state.State

abstract class Participant(val name: String, val deck: Deck) {
    var wallet = ParticipantWallet()
    var state: State = Started(Hand(), deck)

    open fun playTurn() {
        state = state.run()
    }

    fun stay() {
        state = state.stay()
    }

    fun bet(value: Double) {
        wallet.addToBet(value)
    }

    fun updateWalletWithEarningsRate(earningsRate: Double) {
        wallet.updateWithEarningsRate(earningsRate)
    }

    fun calculateEarningsRate(secondParticipant: Participant): Double {
        return ParticipantResult().calculateEarningsRate(this, secondParticipant)
    }
}
