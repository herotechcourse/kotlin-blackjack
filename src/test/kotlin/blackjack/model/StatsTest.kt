package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StatsTest {
    @Test
    fun `playerBoard - Stats make good player board that represent state of player`() {
        // player1 -> bust
        val player1 = Player("player1")
        player1.drawCard(Card("Q♦"))
        player1.drawCard(Card("K♦"))
        player1.drawCard(Card("J♠"))

        // player2 -> win
        val player2 = Player("player2")
        player2.drawCard(Card("10♦"))
        player2.drawCard(Card("A♦"))

        // player3 -> tie
        val player3 = Player("player3")
        player3.drawCard(Card("9♦"))
        player3.drawCard(Card("J♦"))

        val dealer = Dealer()
        dealer.drawCard(Card("3♦"))
        dealer.drawCard(Card("6♦"))
        dealer.drawCard(Card("10♠"))

        val stats = Stats(listOf(player1, player2, player3), dealer)
        val board = stats.playerBoard
        assertEquals(0, board[player1])
        assertEquals(1, board[player2])
        assertEquals(2, board[player3])
    }

    @Test
    fun `updateDealerStats() - method update dealer's stats`() {
        // player1 -> bust
        val player1 = Player("player1")
        player1.drawCard(Card("Q♦"))
        player1.drawCard(Card("K♦"))
        player1.drawCard(Card("J♠"))

        // player2 -> win
        val player2 = Player("player2")
        player2.drawCard(Card("10♦"))
        player2.drawCard(Card("A♦"))

        // player3 -> tie
        val player3 = Player("player3")
        player3.drawCard(Card("9♦"))
        player3.drawCard(Card("J♦"))

        val dealer = Dealer()
        dealer.drawCard(Card("3♦"))
        dealer.drawCard(Card("6♦"))
        dealer.drawCard(Card("10♠"))

        val stats = Stats(listOf(player1, player2, player3), dealer)
        stats.updateDealerStats()
        val dealerStats = stats.dealerStats
        assertEquals(1, dealerStats["win"])
        assertEquals(1, dealerStats["lose"])
        assertEquals(1, dealerStats["tie"])
    }
}
