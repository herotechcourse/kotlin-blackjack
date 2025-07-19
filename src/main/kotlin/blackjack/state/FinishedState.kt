package blackjack.state

import blackjack.model.Card
import blackjack.model.Hand

interface Finished : State {
    override fun draw(card: Card): State {
        throw IllegalStateException("Cannot draw in a finished state.")
    }

    override fun stay(): State {
        throw IllegalStateException("Cannot stay in a finished state.")
    }

    override fun isRunning(): Boolean = false

    override fun isFirstTurn(): Boolean = false
}

class Blackjack(override val hand: Hand) : Finished {
    override val isBlackJack: Boolean = true

    override fun profit(
        dealerState: State,
        betMoney: Int,
    ): Double {
        return if (dealerState.isBlackJack) {
            betMoney * DRAW_TIE
        } else {
            betMoney * BLACKJACK_WIN
        }
    }

    companion object {
        private const val DRAW_TIE = 0.0
        private const val BLACKJACK_WIN = 1.5
    }
}

class Bust(override val hand: Hand) : Finished {
    override fun profit(
        dealerState: State,
        betMoney: Int,
    ): Double {
        return betMoney * LOSE_LOSS
    }

    companion object {
        private const val LOSE_LOSS = -1.0
    }
}

class Stay(override val hand: Hand) : Finished {
    override fun profit(
        dealerState: State,
        betMoney: Int,
    ): Double {
        val playerPoints = hand.calculateSum()
        val dealerPoints = dealerState.hand.calculateSum()

        return when {
            dealerState.isBlackJack -> betMoney * BLACKJACK_LOSE
            dealerState is Bust -> betMoney * WIN_GAIN
            playerPoints > dealerPoints -> betMoney * WIN_GAIN
            playerPoints < dealerPoints -> betMoney * LOSE_LOSS
            else -> betMoney * DRAW_TIE
        }
    }

    companion object {
        private const val WIN_GAIN = 1.0
        private const val LOSE_LOSS = -1.0
        private const val DRAW_TIE = 0.0
        private const val BLACKJACK_LOSE = -1.5
    }
}
