package blackjack.state

import blackjack.model.Card
import blackjack.model.Hand

interface State {
    val hand: Hand

    fun draw(card: Card): State

    fun stay(): State

    val isBlackJack: Boolean
        get() = false

    fun profit(
        dealerState: State,
        betMoney: Int,
    ): Double

    fun isRunning(): Boolean

    fun isFirstTurn(): Boolean
}
