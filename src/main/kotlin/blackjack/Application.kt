package blackjack

import blackjack.controller.Game
import blackjack.model.Dealer

fun main() {
    val game =
        Game(dealer = Dealer())
    game.startGame()
}
