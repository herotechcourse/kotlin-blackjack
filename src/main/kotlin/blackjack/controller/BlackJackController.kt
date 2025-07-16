package blackjack.controller

import blackjack.model.BlackjackGame
import blackjack.model.GameLogic
import blackjack.model.ParticipantsFactory
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {
    fun startGame() {
        try {
            val playersNames = InputView.getPlayersName()
            val players = ParticipantsFactory.generatePlayers(playersNames)
            val dealer = ParticipantsFactory.generateDealer()

            players.forEach {
                val bettingAmount = InputView.bettingAmountInput(it)
                it.setBettingAmount(bettingAmount)
            }

            val blackjackGame =
                BlackjackGame(
                    dealer,
                    players,
                    InputView::askForCard,
                    OutputView::displayPlayer,
                )

            OutputView.displayTableSetUp(dealer, players)
            blackjackGame.play()
            OutputView.displayDealerStats(dealer)
            OutputView.displayFinalResult(dealer, players)

            val result = GameLogic.calculateProfitRates(dealer, players)
            OutputView.printFinalRates(result)
        } catch (e: Exception) {
            println("Error starting the game: ${e.message}")
        }
    }
}
