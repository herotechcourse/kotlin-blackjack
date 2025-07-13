package blackjack.controller

import blackjack.model.holder.Deck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Participants
import blackjack.model.state.State
import blackjack.view.OutputView

class GameManager(
    private val participants: Participants,
) {
    private val cardDeck = Deck()
    private val dealer = participants.getDealer()
    private val players = participants.getPlayers()

    fun setUp() {
        players.forEach { cardDeck.hit(it, 2) }
        cardDeck.hit(dealer)
    }

    fun playGame(
        askForCard: () -> Boolean = { true },
    ) {
        players.forEach { round(it, askForCard) }
        round(dealer)
    }

    internal fun round(
        participant: Participant,
        askForCard: () -> Boolean = { true },
    ) {
        when (participant) {
            is Dealer -> playDealerRound(participant)
            else -> playPlayerRound(participant, askForCard)
        }
    }
    private fun canReceiveCard(participant: Participant): Boolean {
        return participant.state == State.HIT
    }

    private fun playPlayerRound(
        participant: Participant,
        askForCard: () -> Boolean,
    ) {
        while (canReceiveCard(participant)) {
            OutputView.askHit(participant)
            if (askForCard()) {
                cardDeck.hit(participant)
                OutputView.showCards(participant)
            } else {
                break
            }
        }
    }

    private fun playDealerRound(dealer: Dealer) {
        while (canReceiveCard(dealer)) {
            cardDeck.hit(dealer)
        }
        OutputView.showDealerDraw(dealer)
    }
}
