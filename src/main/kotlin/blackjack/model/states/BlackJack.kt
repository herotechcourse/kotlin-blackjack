package blackjack.model.states

import blackjack.model.Bet
import blackjack.model.cards.HandCards

class BlackJack(
    override val hand: HandCards = HandCards(),
    override val rate: Double = 1.5,
) : Stopped() {
    init {
        if (hand.total() != 21 && hand.size() != 2) {
            throw IllegalStateException()
        }
    }

    override fun profit(
        bet: Bet,
        dealerState: State,
    ): Double {
        if (dealerState is BlackJack) {
            return DRAW
        }
        return bet.amount * rate
    }
}
