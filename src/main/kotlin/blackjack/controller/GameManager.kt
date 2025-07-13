package blackjack.controller

import blackjack.model.PlayResult
import blackjack.model.holder.Deck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Participants
import blackjack.model.state.State
import blackjack.view.InputView
import blackjack.view.OutputView

class GameManager(
    private val participants: Participants,
) {
    private val cardDeck = Deck()
    private val dealer = participants.getDealer()
    private val players = participants.getPlayers()

    fun play(): PlayResult {
        setUp()
        OutputView.showPayersCards(participants)
        keepPlay(InputView::readUserAnswer)
        return PlayResult(participants)
    }

    private fun setUp() {
        players.forEach { cardDeck.hit(it, 2) }
        cardDeck.hit(dealer)
    }

    private fun keepPlay(
        askForCard: () -> Boolean = { true },
    ) {
        players.forEach { round(it, askForCard) }
        round(dealer)
    }

    internal fun round(
        participant: Participant,
        getPlayerChoice: () -> Boolean = { true },
    ) {
        when (participant) {
            is Dealer -> playDealerRound(participant)
            else -> playPlayerRound(participant, getPlayerChoice)
        }
    }

    private fun canReceiveCard(participant: Participant): Boolean {
        return participant.state == State.HIT
    }

    private fun playPlayerRound(
        participant: Participant,
        getPlayerChoice: () -> Boolean,
    ) {
        while (canReceiveCard(participant)) {
            OutputView.askHit(participant)
            if (getPlayerChoice()) {
                cardDeck.hit(participant)
                OutputView.showCards(participant)
            } else {
                participant.state == State.STAY
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
