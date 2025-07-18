package blackjack.controller

import blackjack.game.BlackJackGame
import blackjack.game.GameLogic
import blackjack.model.GenerateParticipants
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    fun startGame() {
        try {
            val playersNames = InputView.getPlayersNames()
            val players = GenerateParticipants.generatePlayers(playersNames)
            val dealer = GenerateParticipants.generateDealer()

            players.forEach {
                val bettingAmount = InputView.getBettingAmount(it)
                it.bettingAmount = bettingAmount
            }

            val blackjackGame =
                BlackJackGame(
                    dealer,
                    players,
                    InputView::getAnswer,
                    OutputView::printPlayerHand,
                )

            OutputView.printParticipantsHands(players, dealer)

            blackjackGame.start()

            OutputView.printFinalHands(players, dealer)

            GameLogic.calculateProfitRates(dealer, players)
            OutputView.printWinnings(players, dealer)
        } catch (e: Exception) {
            println("An error occurred during the game: ${e.message}")
        }
    }
}
