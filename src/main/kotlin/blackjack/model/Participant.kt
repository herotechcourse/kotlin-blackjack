package blackjack.model

import blackjack.state.FirstTurn
import blackjack.state.State

abstract class Participant(val name: String, var handState: State = FirstTurn()) {
    val points get() = handState.hand.calculateSum()

    init {
        require(name.isNotBlank()) { "Wrong name: $name. Participant name should not be blank." }
    }

    fun draw(card: Card) {
        handState = handState.draw(card)
    }

    fun stay() {
        handState = handState.stay()
    }

    fun dealInitialCards(deck: Deck) {
        repeat(2) {
            draw(deck.drawCard())
        }
    }

    override fun toString(): String {
        val cardsString = handState.hand.cards.joinToString(", ") {
            it.rank.symbol + it.suit.symbol
        }
        return "$name: $cardsString"
    }
}
