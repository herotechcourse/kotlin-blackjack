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

    private fun round(
        participant: Participant,
        getPlayerChoice: () -> Boolean = { true },
    ) {
        when (participant) {
            is Dealer -> playDealerRound(participant, OutputView::showDealerDraw)
            else -> playPlayerRoundWithView(participant, getPlayerChoice)
        }
    }

    private fun canReceiveCard(participant: Participant): Boolean {
        return participant.state == State.HIT
    }

    private fun playPlayerRoundWithView(
        participant: Participant,
        getPlayerChoice: () -> Boolean,
    ) {
        playPlayerRound(
            participant,
            getPlayerChoice,
            ask = OutputView::askHit,
            show = OutputView::showCards,
        )
    }

    internal fun playPlayerRound(
        participant: Participant,
        getPlayerChoice: () -> Boolean,
        ask: (Participant) -> Unit = {},
        show: (Participant) -> Unit = {},
    ) {
        while (canReceiveCard(participant) && deck.cards.isNotEmpty()) {
            ask(participant)
            if (getPlayerChoice()) {
                deck.hit(participant)
                show(participant)
            } else {
                break
            }
        }
    }

    internal fun playDealerRound(
        dealer: Dealer,
        show: (Dealer) -> Unit = {},
    ) {
        while (canReceiveCard(dealer) && deck.cards.isNotEmpty()) {
            deck.hit(dealer)
        }
        show(dealer)
    }

    internal fun getPlayers(): List<Player> = players

    internal fun getDealer(): Dealer = dealer

    internal fun getDeck(): Deck = deck
}
