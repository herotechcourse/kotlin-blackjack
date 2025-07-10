package blackjack

import blackjack.controller.Game
import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val game =
        Game(
            inputView = InputView(),
            outputView = OutputView(),
            deck = Deck(),
            dealer = Dealer(),
        )
    game.startGame()
}
