package blackjack.model

import blackjack.Fixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StatsTest {
    @Test
    fun `payOutPotToEarnings() - method calculate pay-out for players and dealer`() {
        // player1 -> bust
        val player1 = Player("player1").placeBets(100)
        player1.drawCard(Fixture.DIAMONDS_TEN)
        player1.drawCard(Fixture.DIAMONDS_JACK)
        player1.drawCard(Fixture.DIAMONDS_QUEEN)

        // player2 -> win
        val player2 = Player("player2").placeBets(200)
        player2.drawCard(Fixture.DIAMONDS_TEN)
        player2.drawCard(Fixture.DIAMONDS_QUEEN)

        // player3 -> tie
        val player3 = Player("player3").placeBets(300)
        player3.drawCard(Fixture.DIAMONDS_NINE)
        player3.drawCard(Fixture.DIAMONDS_JACK)

        val dealer = Dealer()
        dealer.drawCard(Fixture.DIAMONDS_NINE)
        dealer.drawCard(Fixture.DIAMONDS_JACK)

        val stats = Stats(listOf(player1, player2, player3), dealer)
        val earningMap = stats.payOutPotToEarnings()
        assertEquals(-100, earningMap[player1])
        assertEquals(200, earningMap[player2])
        assertEquals(0, earningMap[player3])
        assertEquals(-100, earningMap[dealer])
    }

    @Test
    fun `payOutPotToEarnings() - a player win with blackjack`() {
        // player1 -> bust
        val player1 = Player("player1").placeBets(100)
        player1.drawCard(Fixture.DIAMONDS_TEN)
        player1.drawCard(Fixture.DIAMONDS_JACK)
        player1.drawCard(Fixture.DIAMONDS_QUEEN)

        // player2 -> win
        val player2 = Player("player2").placeBets(200)
        player2.drawCard(Fixture.DIAMONDS_TEN)
        player2.drawCard(Fixture.DIAMONDS_ACE)

        // player3 -> tie
        val player3 = Player("player3").placeBets(300)
        player3.drawCard(Fixture.DIAMONDS_NINE)
        player3.drawCard(Fixture.DIAMONDS_JACK)

        val dealer = Dealer()
        dealer.drawCard(Fixture.DIAMONDS_NINE)
        dealer.drawCard(Fixture.DIAMONDS_JACK)

        val stats = Stats(listOf(player1, player2, player3), dealer)
        val earningMap = stats.payOutPotToEarnings()
        assertEquals(-100, earningMap[player1])
        assertEquals(300, earningMap[player2])
        assertEquals(0, earningMap[player3])
        assertEquals(-200, earningMap[dealer])
    }

    @Test
    fun `payOutPotToEarnings() - dealer win with blackjack`() {
        // player1 -> bust
        val player1 = Player("player1").placeBets(100)
        player1.drawCard(Fixture.DIAMONDS_TEN)
        player1.drawCard(Fixture.DIAMONDS_JACK)
        player1.drawCard(Fixture.DIAMONDS_QUEEN)

        // player2 -> lost
        val player2 = Player("player2").placeBets(200)
        player2.drawCard(Fixture.DIAMONDS_TEN)
        player2.drawCard(Fixture.DIAMONDS_QUEEN)

        // player3 -> lost
        val player3 = Player("player3").placeBets(300)
        player3.drawCard(Fixture.DIAMONDS_NINE)
        player3.drawCard(Fixture.DIAMONDS_JACK)

        val dealer = Dealer()
        dealer.drawCard(Fixture.DIAMONDS_JACK)
        dealer.drawCard(Fixture.DIAMONDS_ACE)

        val stats = Stats(listOf(player1, player2, player3), dealer)
        val earningMap = stats.payOutPotToEarnings()
        assertEquals(-100, earningMap[player1])
        assertEquals(-200, earningMap[player2])
        assertEquals(-300, earningMap[player3])
        assertEquals(600, earningMap[dealer])
    }

    @Test
    fun `payOutPotToEarnings() - a player and the dealer are blackjack`() {
        // player1 -> bust
        val player1 = Player("player1").placeBets(100)
        player1.drawCard(Fixture.DIAMONDS_TEN)
        player1.drawCard(Fixture.DIAMONDS_JACK)
        player1.drawCard(Fixture.DIAMONDS_QUEEN)

        // player2 -> blackjack
        val player2 = Player("player2").placeBets(200)
        player2.drawCard(Fixture.DIAMONDS_TEN)
        player2.drawCard(Fixture.DIAMONDS_ACE)

        // player3 -> lost
        val player3 = Player("player3").placeBets(300)
        player3.drawCard(Fixture.DIAMONDS_NINE)
        player3.drawCard(Fixture.DIAMONDS_JACK)

        val dealer = Dealer()
        dealer.drawCard(Fixture.DIAMONDS_JACK)
        dealer.drawCard(Fixture.DIAMONDS_ACE)

        val stats = Stats(listOf(player1, player2, player3), dealer)
        val earningMap = stats.payOutPotToEarnings()
        assertEquals(-100, earningMap[player1])
        assertEquals(0, earningMap[player2])
        assertEquals(-300, earningMap[player3])
        assertEquals(400, earningMap[dealer])
    }
}
