package blackjack.model

import blackjack.Fixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StatsTest {
    @Test
    fun `playerBoard - Stats make good player board that represent state of player`() {
        // player1 -> bust
        val player1 = Player("player1")
        player1.drawCard(Fixture.DIAMONDS_TEN)
        player1.drawCard(Fixture.DIAMONDS_JACK)
        player1.drawCard(Fixture.DIAMONDS_QUEEN)

        // player2 -> win
        val player2 = Player("player2")
        player2.drawCard(Fixture.DIAMONDS_TEN)
        player2.drawCard(Fixture.DIAMONDS_ACE)

        // player3 -> tie
        val player3 = Player("player3")
        player3.drawCard(Fixture.DIAMONDS_NINE)
        player3.drawCard(Fixture.DIAMONDS_JACK)

        val dealer = Dealer()
        dealer.drawCard(Fixture.DIAMONDS_NINE)
        dealer.drawCard(Fixture.DIAMONDS_JACK)

        val stats = Stats(listOf(player1, player2, player3), dealer)
        val board = stats.playerBoard
        assertEquals(Result.LOSE, board[player1])
        assertEquals(Result.WIN, board[player2])
        assertEquals(Result.TIE, board[player3])
    }

    @Test
    fun `updateDealerStats() - method update dealer's stats`() {
        // player1 -> bust
        val player1 = Player("player1")
        player1.drawCard(Fixture.DIAMONDS_TEN)
        player1.drawCard(Fixture.DIAMONDS_JACK)
        player1.drawCard(Fixture.DIAMONDS_QUEEN)

        // player2 -> win
        val player2 = Player("player2")
        player2.drawCard(Fixture.DIAMONDS_TEN)
        player2.drawCard(Fixture.DIAMONDS_ACE)

        // player3 -> tie
        val player3 = Player("player3")
        player3.drawCard(Fixture.DIAMONDS_NINE)
        player3.drawCard(Fixture.DIAMONDS_JACK)

        val dealer = Dealer()
        dealer.drawCard(Fixture.DIAMONDS_NINE)
        dealer.drawCard(Fixture.DIAMONDS_JACK)

        val stats = Stats(listOf(player1, player2, player3), dealer)
        stats.updateDealerStats()
        val dealerStats = stats.dealerStats
        assertEquals(1, dealerStats[Result.WIN])
        assertEquals(1, dealerStats[Result.LOSE])
        assertEquals(1, dealerStats[Result.TIE])
    }
}
