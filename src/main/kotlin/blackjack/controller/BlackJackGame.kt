package blackjack.controller

import blackjack.model.GameJudge
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import java.lang.Exception
import kotlin.system.exitProcess

object BlackJackGame {
    private const val RETRY_LIMIT = 10
    private const val YES = "y"
    private const val INITIAL_DEALER_CARDS = 2

    fun start() {
        val participants = Participants(Dealer(), createPlayers())
        participants.dealer.shuffleDeck()
        dealFirstCards(participants)
        dealingPlayersCards(participants)
        dealingDealersCards(participants.dealer)
        calculateResults(participants)
    }

    private fun createPlayers(): List<Player> {
        return retryUntilSuccess { InputView.getPlayersNames() }.map { Player(it) }
    }

    private fun dealFirstCards(participants: Participants) {
        repeat(INITIAL_DEALER_CARDS) { participants.dealOneCardToAll() }
        participants.sortAllHands()
        OutputView.printParticipantsHands(participants)
    }

    private fun dealingPlayersCards(participants: Participants) {
        participants.players.forEach { player ->
            dealCardsTo(
                player,
                participants.dealer,
            ) { retryUntilSuccess { InputView.getAnswer(player.name) == YES } }
        }
        OutputView.printEmptyLine()
    }

    private fun dealCardsTo(
        player: Player,
        dealer: Dealer,
        shouldHit: () -> Boolean,
    ) {
        if (player.hasBlackJack()) {
            OutputView.printPlayerInfo(player)
            return
        }
        while (!player.isBusts() && shouldHit()) {
            player.addCard(dealer.dealCard())
            OutputView.printPlayerInfo(player)
        }
    }

    private fun dealingDealersCards(dealer: Dealer) {
        var drewCard = false
        while (dealer.shouldNotStand()) {
            drewCard = true
            dealer.addCard(dealer.dealCard())
            OutputView.printDealersDrawMessage()
        }
        if (!drewCard) OutputView.printDealersStandMessage()
        dealer.showAllCards()
    }

    private fun calculateResults(participants: Participants) {
        OutputView.printFinalHands(participants)
        GameJudge.evaluateAll(participants)
        OutputView.printResults(participants)
    }

    internal fun <T> retryUntilSuccess(block: () -> T): T {
        repeat(RETRY_LIMIT) {
            try {
                return block()
            } catch (e: Exception) {
                OutputView.printErrorMessage(e.message ?: "Unknown Error. Try again.")
            }
        }
        OutputView.printErrorMessage("You tried so many times. Sorry the Game is over. Bye!!")
        exitProcess(1)
    }
}
