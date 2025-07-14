package blackjack.model

interface State {
    val hand: Hand

    fun draw(card: Card): State

    fun stay(): State

    fun profit(money: Int): Double
}

abstract class Running : State {
    override fun profit(money: Int): Double {
        throw IllegalStateException("Cannot calculate profit in a running state.")
    }
}

abstract class Finished : State {
    abstract val rate: Double

    override fun draw(card: Card): State {
        throw IllegalStateException("Cannot draw in a finished state.")
    }

    override fun stay(): State {
        throw IllegalStateException("Cannot stay in a finished state.")
    }

    override fun profit(money: Int): Double {
        return money * rate
    }
}

class FirstTurn(override val hand: Hand = Hand(emptyList())) : Running() {
    override fun draw(card: Card): State {
        val newHand = this.hand + card
        if (newHand.size == 2) {
            if (newHand.getScore() == Hand.BLACK_JACK) return Blackjack(newHand)
            return Hit(newHand)
        }
        return FirstTurn(newHand)
    }

    override fun stay(): State {
        throw IllegalStateException("Cannot stay in a finished state.")
    }
}

class Hit(override val hand: Hand) : Running() {
    init {
        require(hand.size >= 2) { "Hit state requires at least 2 cards in hand." }
    }

    override fun draw(card: Card): State {
        val newHand = this.hand + card
        if (newHand.getScore() > Hand.BLACK_JACK) return Bust(newHand)
        return Hit(newHand)
    }

    override fun stay(): State {
        return Stay(hand)
    }
}

class Blackjack(override val hand: Hand, override val rate: Double = 1.5) : Finished()

class Stay(override val hand: Hand, override val rate: Double = 1.0) : Finished()

class Bust(override val hand: Hand, override val rate: Double = 0.0) : Finished()
