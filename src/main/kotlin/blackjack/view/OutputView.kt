package blackjack.view

import blackjack.model.Participant

class OutputView {
    fun displayHandCards(participant: Participant) {
        println(
            "${participant.name}'s cards: ${
                participant
                    .cardsInHand
                    .joinToString(", ") { card -> (card.rank.face + card.suit.symbol) }
            }",
        )
    }
}
