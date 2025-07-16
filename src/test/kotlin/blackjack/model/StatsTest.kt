package blackjack.model

import blackjack.view.StatsView
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StatsTest {
    @Test
    fun `playerBoard - Stats make good player board that represent state of player`() {
        // player1 -> bust
        val bet = Bet(10000)
        val player1 = Player("player1", bet)
        player1.drawCard(Card(Rank.TWO, Suit.DIAMONDS))
        player1.drawCard(Card(Rank.KING, Suit.DIAMONDS))
        player1.drawCard(Card(Rank.JACK, Suit.SPADES))

        // player2 -> win
        val player2 = Player("player2", bet)
        player2.drawCard(Card(Rank.TEN, Suit.DIAMONDS))
        player2.drawCard(Card(Rank.ACE, Suit.DIAMONDS))

        // player3 -> tie
        val player3 = Player("player3", bet)
        player3.drawCard(Card(Rank.NINE, Suit.DIAMONDS))
        player3.drawCard(Card(Rank.JACK, Suit.DIAMONDS))

        val dealer = Dealer()
        dealer.drawCard(Card(Rank.THREE, Suit.DIAMONDS))
        dealer.drawCard(Card(Rank.SIX, Suit.DIAMONDS))
        dealer.drawCard(Card(Rank.TEN, Suit.SPADES))

        val stats = StatsView(listOf(player1, player2, player3), dealer)
        val board = stats.playerBoard
        assertEquals(EarningsResult.LOSE_BET, board[player1])
        assertEquals(EarningsResult.WIN_BLACK_JACK_BET, board[player2])
        assertEquals(EarningsResult.TIE_BET, board[player3])
    }

    @Test
    fun `updateDealerStats() - method update dealer's stats`() {
        // player1 -> bust
        val bet = Bet(10000)
        val player1 = Player("player1", bet)
        player1.drawCard(Card(Rank.QUEEN, Suit.DIAMONDS))
        player1.drawCard(Card(Rank.KING, Suit.DIAMONDS))
        player1.drawCard(Card(Rank.JACK, Suit.SPADES))

        // player2 -> win
        val player2 = Player("player2", bet)
        player2.drawCard(Card(Rank.TEN, Suit.DIAMONDS))
        player2.drawCard(Card(Rank.ACE, Suit.DIAMONDS))

        // player3 -> tie
        val player3 = Player("player3", bet)
        player3.drawCard(Card(Rank.NINE, Suit.DIAMONDS))
        player3.drawCard(Card(Rank.JACK, Suit.DIAMONDS))

        val dealer = Dealer()
        dealer.drawCard(Card(Rank.THREE, Suit.DIAMONDS))
        dealer.drawCard(Card(Rank.SIX, Suit.DIAMONDS))
        dealer.drawCard(Card(Rank.TEN, Suit.SPADES))

        val stats = StatsView(listOf(player1, player2, player3), dealer)
        stats.updateDealerStats()
        val dealerStats = stats.dealerStats
        assertEquals(1, dealerStats["win"])
        assertEquals(1, dealerStats["lose"])
        assertEquals(1, dealerStats["tie"])
    }
}
