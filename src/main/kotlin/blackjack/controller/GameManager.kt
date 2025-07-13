package blackjack.controller

import blackjack.model.holder.Deck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Participants
import blackjack.model.participant.Player
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
            is Dealer -> dealerPlay(participant)
            is Player -> playersPlay(participant, askForCard)
        }
        throw IllegalStateException()
    }

    private fun ableToReceive(participant: Participant): Boolean {
        when (participant) {
            is Dealer -> return participant.points <= ABLE_TO_RECEIVE
            is Player -> participant.points < BLACKJACK
        }
        throw IllegalStateException()
    }

    private fun playersPlay(
        participant: Participant,
        askForCard: () -> Boolean,
    ) {
        while (ableToReceive(participant)) {
            OutputView.printAskForCard(participant)
            if (askForCard()) {
                cardDeck.hit(participant)
                OutputView.printOnePlayer(participant)
            } else {
                break
            }
        }
    }

    private fun dealerPlay(dealer: Dealer) {
        while (ableToReceive(dealer)) {
            cardDeck.hit(dealer)
        }
        OutputView.printDealerDrawsCards(dealer)
    }
}
