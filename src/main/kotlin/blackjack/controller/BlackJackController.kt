package blackjack.controller

import blackjack.model.GameManager
import blackjack.model.PlayerFactory

object BlackJackController {
    fun play() {
        // show prompt
        // ask/read user input: players name
        val names = listOf("mina", "vito")

        // create players, create dealer -> store
        val players = PlayerFactory.with(names)
        val dealer = PlayerFactory.createDealer()

        val totalPlayer = players + dealer
        // give it to manager && set up
        val gameManager = GameManager(dealer, players)
        val setupResult = gameManager.setUp()

//        OutputView.printPlayerResult(setupResult)

        val totalPlayResult =
            gameManager
                .finalResult(
                    totalPlayer,
                    { true },
                )

//        OutputView.Print(totalPlayResult)
    }
}
