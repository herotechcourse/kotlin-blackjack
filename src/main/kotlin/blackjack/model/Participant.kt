package blackjack.model

import blackjack.states.FirstTurn
import blackjack.states.State

abstract class Participant(
    val name: String,
    var state: State = FirstTurn(),
) {
    val points get() = state.hand.calculatePoints()

    fun draw(card: Card) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }

    fun dealInitialCards(cardDeck: CardDeck) {
        repeat(2) {
            draw(cardDeck.drawCard())
        }
    }
}
