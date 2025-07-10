package blackjack.controller

import blackjack.model.GameManager
import blackjack.model.PlayerFactory
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

            val totalPlayResult = gameManager.playGame(totalPlayer) { InputView.askForCard() }

            OutputView.printAllPlayers(listOf(dealer) + players)

//        OutputView.Print(totalPlayResult)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}
