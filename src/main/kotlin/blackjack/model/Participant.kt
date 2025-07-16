package blackjack.model

import blackjack.states.State

abstract class Participant(
    val name: String,
    var state: State,
) {
    val points get() = state.hand.calculatePoints()

    fun draw(card: Card) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }

    fun hitTwoCards(cardDeck: CardDeck) {
        while (state.isFirstTurn()) draw(cardDeck.drawCard())
    }
}
