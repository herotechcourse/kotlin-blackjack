package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import java.lang.Exception
import kotlin.system.exitProcess

object BlackJackGame {
    private const val RETRY_LIMIT = 100
    private const val YES = "y"

    fun start() {
        val players = createPlayers()
        val dealer = Dealer().also { it.shuffleDeck() }
        dealFirstCards(players, dealer)
        dealingPlayersCards(players, dealer)
        dealingDealersCards(dealer)
        calculateResults(players, dealer)
    }

    internal fun createPlayers(): List<Player> {
        return retryUntilSuccess { InputView.getPlayersNames() }.map { Player(it) }
    }

    internal fun dealFirstCards(players: List<Player>, dealer: Dealer) {
        repeat(2) {
            dealer.addCard(dealer.dealCard())
            players.forEach { it.addCard(dealer.dealCard()) }
        }
        OutputView.printParticipantsHands(players, dealer)
    }

    internal fun dealingPlayersCards(players: List<Player>, dealer: Dealer) {
        for (player in players) {
            while (!player.hasBlackJack() && !player.isBusts()) {
                val answer = retryUntilSuccess { InputView.getAnswer(player.name) }
                if (answer == YES) {
                    val card = dealer.dealCard()
                    player.addCard(card)
                    println(player)
                } else {
                    break
                }
            }
        }
    }

    internal fun dealingDealersCards(dealer: Dealer) {
        var isDealerHitACard = false
        while (dealer.shouldNotStand()) {
            isDealerHitACard = true
            dealer.addCard(dealer.dealCard())
            OutputView.printDealersDrawMessage()
        }
        if (isDealerHitACard) {
            OutputView.printDealersStandMessage()
        }
        dealer.showAllCards()
    }

    internal fun calculateResults(players: List<Player>, dealer: Dealer) {
        OutputView.printFinalHands(players, dealer)

        players.forEach { dealer.setResultFor(it) }

        OutputView.printResults(players, dealer)
    }

    internal fun <T> retryUntilSuccess(block: () -> T): T {
        repeat(RETRY_LIMIT) {
            try {
                return block()
            } catch (e: Exception) {
                OutputView.showErrorMessage(e.message ?: "Unknown Error. Try again.")
            }
        }
        OutputView.showErrorMessage("You tried so many times. Sorry the Game is over. Bye!!")
        exitProcess(1)
    }
}
