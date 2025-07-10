package model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `deal 2 cards for each player at the start of the game`() {
        val dealer = Dealer()
        val players = listOf("A", "B", "C", "D", "E", "F").map { Player(it) }
        BackJack.initGame(players, dealer)
        Assertions.assertTrue { players.all { it.getCardsNumber() == 2 } }
        Assertions.assertTrue { dealer.getCardsNumber() == 2 }
    }
}
