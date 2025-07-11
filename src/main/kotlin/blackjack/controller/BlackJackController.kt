package blackjack.controller

import blackjack.controller.GameManager
import blackjack.model.PlayerFactory
import blackjack.model.Statistics
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackJackController {
    fun play() {
        try {
            val names = InputView.getPlayersName()
            val players = PlayerFactory.with(names)
            val dealer = PlayerFactory.createDealer()

            val gameManager = GameManager(dealer, players)
            gameManager.setUp()
            OutputView.printAllPlayers(listOf(dealer) + players)
            gameManager.playGame(dealer, players) { InputView.askForCard() }

            val statistics = Statistics(dealer, players)
            OutputView.printFinalResults(listOf(dealer) + players)
            OutputView.printStatistics(statistics)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}
