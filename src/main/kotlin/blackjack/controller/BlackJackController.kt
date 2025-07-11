package blackjack.controller

import blackjack.model.GameManager
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
            OutputView.printAllPlayers(listOf(dealer) + players)
            gameManager.playGame(dealer, players) { InputView.askForCard() }

            OutputView.printFinalResults(listOf(dealer) + players)
            val statistics = Statistics(dealer, players)
            OutputView.printStatistics(statistics)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}
