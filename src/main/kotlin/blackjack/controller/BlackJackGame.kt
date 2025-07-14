package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView
import java.lang.Exception
import kotlin.system.exitProcess

class BlackJackGame {
    val dealer = Dealer()
    lateinit var players: Players

    fun start() {
        createPlayers()
        players.dealFirstCards(dealer)
        OutputView.printParticipantsHands(players.toList(), dealer)
        dealingPlayersCards()
        dealingDealersCards()
        OutputView.printFinalHands(players.toList(), dealer)
        players.calculateResults(dealer)
        OutputView.printWinnings(players.toList(), dealer)
    }

    private fun createPlayers() {
        val playerNames: List<String> = retryUntilSuccess { InputView.getPlayersNames() }
        val playerList: List<Player> =
            playerNames.map { playerName ->
                val bettingAmount: Int =
                    retryUntilSuccess {
                        InputView.getBettingAmount(playerName)
                    }
                Player(playerName, bettingAmount)
            }
        players = Players(playerList)
    }

    fun dealingPlayersCards() {
        for (player in players.dealingPlayersCards()) {
            while (!player.isFinished()) {
                val answer = retryUntilSuccess { InputView.getAnswer(player.name) }
                if (answer == "y") {
                    player.addCard(dealer.dealCard())
                    println(player)
                } else {
                    player.stay()
                    break
                }
            }
        }
    }

    private fun dealingDealersCards() {
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
