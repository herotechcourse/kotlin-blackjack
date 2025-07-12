package blackjack

import blackjack.controller.Game
import blackjack.model.Dealer
import blackjack.model.Deck

fun main() {
    val game =
        Game(
            deck = Deck(),
            dealer = Dealer(),
        )
    game.startGame()
}
