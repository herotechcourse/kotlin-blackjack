package blackjack.controller

import blackjack.model.holder.Deck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import blackjack.model.result.GameResult
import blackjack.model.state.State
import blackjack.view.InputView
import blackjack.view.OutputView

class GameManager(
    private val participants: Participants,
) {
    private val deck = Deck()
    private val dealer = participants.dealer
    private val players = participants.players

    fun play(): GameResult {
        setUp()
        OutputView.showFirstRound(participants)
        keepPlay(InputView::readUserAnswer)
        return GameResult(participants)
    }

    private fun setUp() {
        players.forEach { deck.hit(it, 2) }
        deck.hit(dealer)
    }

    private fun keepPlay(askForCard: () -> Boolean = { true }) {
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

    internal fun playPlayerRound(
        participant: Participant,
        getPlayerChoice: () -> Boolean,
    ) {
        while (canReceiveCard(participant) && deck.cards.isNotEmpty()) {
            OutputView.askHit(participant)
            if (getPlayerChoice()) {
                deck.hit(participant)
                OutputView.showCards(participant)
            } else {
                break
            }
        }
    }

    private fun playDealerRound(dealer: Dealer) {
        while (canReceiveCard(dealer) && deck.cards.isNotEmpty()) {
            deck.hit(dealer)
        }
        OutputView.showDealerDraw(dealer)
    }

    internal fun getPlayers(): List<Player> = players

    internal fun getDealer(): Dealer = dealer

    internal fun getDeck(): Deck = deck
}
