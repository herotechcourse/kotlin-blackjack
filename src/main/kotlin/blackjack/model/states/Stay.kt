package blackjack.model.states

import blackjack.model.Bet
import blackjack.model.cards.HandCards

class Stay(
    override val hand: HandCards,
    override val rate: Double = 1.0,
) : Stopped() {
    init {
        if (hand.size() < 2) {
            throw IllegalStateException()
        }
    }

    override fun profit(
        bet: Bet,
        dealerState: State,
    ): Double {
        return when (dealerState) {
            is BlackJack -> (bet.amount * LOSS)
            is Bust -> (bet.amount * rate)
            is Stay -> {
                when {
                    (dealerState.hand < this.hand) -> (bet.amount * rate)
                    (dealerState.hand > this.hand) -> (bet.amount * LOSS)
                    else -> DRAW
                }
            }
            else -> (bet.amount * rate)
        }
    }
}
