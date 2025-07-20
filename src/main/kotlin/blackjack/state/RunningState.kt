package blackjack.state

import blackjack.model.Card
import blackjack.model.Hand

interface Running : State {
    override fun isRunning(): Boolean = true

    override fun profit(
        dealerState: State,
        betMoney: Int,
    ): Double {
        throw IllegalStateException("Cannot calculate profit in a running state.")
    }
}

class FirstTurn(override val hand: Hand = Hand()) : Running {
    override fun draw(card: Card): State {
        val newHand = this.hand + card
        if (newHand.size == TWO_CARDS) {
            return if (newHand.calculateSum() == Hand.BLACK_JACK) {
                Blackjack(newHand)
            } else {
                Hit(newHand)
            }
        }
        return FirstTurn(newHand)
    }

    override fun stay(): State {
        throw IllegalStateException("Cannot stay in a running state.")
    }

    override val isBlackJack: Boolean = false

    override fun isFirstTurn(): Boolean = true

    companion object {
        const val TWO_CARDS = 2
    }
}

class Hit(override val hand: Hand) : Running {
    override fun draw(card: Card): State {
        val newHand = this.hand + card
        return if (newHand.calculateSum() > Hand.BLACK_JACK) {
            Bust(newHand)
        } else {
            Hit(newHand)
        }
    }

    override fun stay(): State {
        return Stay(hand)
    }

    override fun isFirstTurn(): Boolean = false
}
