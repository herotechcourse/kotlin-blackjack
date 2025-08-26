package blackjack.model

import blackjack.state.Blackjack
import blackjack.state.Busted

class ParticipantResult {
    fun calculateEarningsRate(
        p1: Participant,
        p2: Participant,
    ): Double {
        val p1Points = p1.state.hand.sumCards()
        val p2Points = p2.state.hand.sumCards()

        return when {
            p1.state is Busted -> -1.0
            p2.state is Busted && p1.state is Blackjack -> 1.5
            p2.state is Busted -> 1.0
            p1Points > p2Points && p1.state is Blackjack -> 1.5
            p1Points > p2Points -> 1.0
            p1Points == p2Points -> 0.0
            else -> -1.0
        }
    }
}
