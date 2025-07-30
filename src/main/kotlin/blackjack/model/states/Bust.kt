package blackjack.model.states

import blackjack.model.Bet
import blackjack.model.cards.HandCards

class Bust(
    override val hand: HandCards,
    override val rate: Double = -1.0,
) : Stopped() {
    init {
        if (hand.total() <= 21) {
            throw IllegalStateException()
        }
    }

    override fun profit(
        bet: Bet,
        dealerState: State,
    ): Double {
        return bet.amount * rate
    }
}
