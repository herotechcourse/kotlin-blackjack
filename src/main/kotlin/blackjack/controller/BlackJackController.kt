package blackjack.controller

import blackjack.model.GameManager
import blackjack.model.PlayerFactory
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackJackController {
    fun play() {
        try {
            // show prompt
            // ask/read user input: players name
            val names = InputView.getPlayersName()

            // create players, create dealer -> store
            val players = PlayerFactory.with(names)
            val dealer = PlayerFactory.createDealer()

            val totalPlayer = players + dealer
            // give it to manager && set up
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
