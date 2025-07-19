package blackjack.controller

import blackjack.game.BlackJackGame
import blackjack.game.GameLogic
import blackjack.model.GenerateParticipants
import blackjack.model.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    fun startGame() {
        try {
            val playersNames = InputView.getPlayersNames()
            val playersList = GenerateParticipants.generatePlayers(playersNames)
            val allPlayers = Players(playersList)

            val dealer = GenerateParticipants.generateDealer()

            playersList.forEach {
                val bettingAmount = InputView.getBettingAmount(it)
                it.bettingAmount = bettingAmount
            }

            val blackjackGame =
                BlackJackGame(
                    dealer,
                    allPlayers,
                    InputView::getAnswer,
                    OutputView::printPlayerHand,
                )

            OutputView.printParticipantsHands(allPlayers.toList(), dealer)

            blackjackGame.start()

            OutputView.printFinalHands(allPlayers.toList(), dealer)

            GameLogic.calculateProfitRates(dealer, allPlayers.toList())
            OutputView.printWinnings(allPlayers.toList(), dealer)
        } catch (e: Exception) {
            println("An error occurred during the game: ${e.message}")
        }
    }
}
