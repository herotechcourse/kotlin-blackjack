package model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `deal 2 cards for each player at the start of the game`() {
        val game = BlackJack(listOf("A", "B", "C", "D", "E", "F"))
        val players = game.players
        val dealer = game.dealer
        Assertions.assertTrue { players.all { it.getCardsNumber() == 2 } }
        Assertions.assertTrue { dealer.getCardsNumber() == 2 }
    }
}
