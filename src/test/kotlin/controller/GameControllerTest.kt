package controller

import model.Dealer
import model.Player
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GameControllerTest {
    @Test
    fun `deal 2 cards for each player at the start of the game`() {
        val dealer = Dealer()
        val players = listOf("A", "B", "C", "D", "E", "F").map { Player(it) }
        GameController.initGame(players, dealer)
        assertTrue { players.all { it.getCardsNumber() == 2 } }
        assertTrue { dealer.getCardsNumber() == 2 }
    }
}
