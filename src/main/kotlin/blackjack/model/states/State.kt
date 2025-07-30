package blackjack.model.states

import blackjack.model.Bet
import blackjack.model.cards.Card
import blackjack.model.cards.HandCards

interface State {
    val hand: HandCards

    fun draw(card: Card): State

    fun stay(): State

    fun profit(
        bet: Bet,
        dealerState: State,
    ): Double
}
