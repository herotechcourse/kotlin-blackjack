package blackjack.controller

import blackjack.model.holder.CardDeck
import blackjack.model.participant.Participant

class GameManager(
    private val participant: Participant,
) {
    private val cardDeck = CardDeck()

    companion object {
        private const val ABLE_TO_RECEIVE = 16
        private const val BLACKJACK = 21
    }
}