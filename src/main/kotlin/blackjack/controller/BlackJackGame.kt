package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import java.lang.Exception
import kotlin.system.exitProcess

class BlackJackGame {
    val dealer = Dealer()
    var players = listOf<Player>()

    fun start() {
        createPlayers()
        dealFirstCards()
        dealingPlayersCards()
        dealingDealersCards()
        calculateResults()
    }

    fun createPlayers() {
        players = retryUntilSuccess { InputView.getPlayersNames() }.map { Player(it) }
    }

    fun dealFirstCards() {
        repeat(2) {
            dealer.addCard(dealer.dealCard())
            players.forEach { it.addCard(dealer.dealCard()) }
        }
        OutputView.printParticipantsHands(players, dealer)
    }

    fun dealingPlayersCards() {
        for (player in players) {
            while (!player.hasBlackJack() && !player.isBusts()) {
                val answer = retryUntilSuccess {  InputView.getAnswer(player.name) }
                if (answer == "y") {
                    val card = dealer.dealCard()
                    player.addCard(card)
                    println(player)
                } else {
                    break
                }
            }
        }
    }

    fun dealingDealersCards() {
        var isDealerHitACard = false
        while (dealer.shouldNotStand()) {
            isDealerHitACard = true
            dealer.addCard(dealer.dealCard())
            OutputView.printDealersDrawMessage()
        }
        if (isDealerHitACard)
            OutputView.printDealersStandMessage()
        dealer.showAllCards()
    }

    fun calculateResults() {
        OutputView.printFinalHands(players, dealer)

        players.forEach { dealer.setResultFor(it) }

        OutputView.printResults(players, dealer)
    }

    private fun <T> retryUntilSuccess(block: () -> T): T {
        repeat(RETRY_LIMIT) {
            try {
                return block()
            } catch (e: Exception) {
                println(e.message)
            }
        }
        exitProcess(1)
    }

    companion object {
        private const val RETRY_LIMIT = 100
    }
}
