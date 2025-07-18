package blackjack

import blackjack.controller.StatsManager
import blackjack.model.Bet
import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.EarningsResult
import blackjack.model.Player
import blackjack.model.Rank
import blackjack.model.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `player wins with higher score than dealer`() {
        val dealer = Dealer("Dealer")
        dealer.drawCard(Card(Rank.NINE, Suit.SPADES)) // 9
        dealer.drawCard(Card(Rank.EIGHT, Suit.CLUBS)) // 8 → 17

        val player = Player("player", Bet(10000))
        player.drawCard(Card(Rank.TEN, Suit.DIAMONDS)) // 10
        player.drawCard(Card(Rank.JACK, Suit.HEARTS)) // 10 → 20

        val statsManager = StatsManager(listOf(player), dealer)
        statsManager.processStatistics(listOf(player), dealer)

        assertEquals(EarningsResult.WIN_BET, statsManager.winStatistics.playerBoard[player])
        assertEquals(10000, statsManager.earnings[player])
        assertEquals(-10000, statsManager.earnings[dealer])
    }

    @Test
    fun `player loses by busting`() {
        val dealer = Dealer("Dealer")
        dealer.drawCard(Card(Rank.NINE, Suit.SPADES)) // 9
        dealer.drawCard(Card(Rank.SIX, Suit.CLUBS)) // 6 → 15

        val player = Player("player", Bet(20000))
        player.drawCard(Card(Rank.QUEEN, Suit.DIAMONDS)) // 10
        player.drawCard(Card(Rank.JACK, Suit.HEARTS)) // 10
        player.drawCard(Card(Rank.THREE, Suit.CLUBS)) // 3 → 23

        val statsManager = StatsManager(listOf(player), dealer)
        statsManager.processStatistics(listOf(player), dealer)

        assertEquals(EarningsResult.LOSE_BET, statsManager.winStatistics.playerBoard[player])
        assertEquals(-20000, statsManager.earnings[player])
        assertEquals(20000, statsManager.earnings[dealer])
    }

    @Test
    fun `player and dealer tie`() {
        val dealer = Dealer("Dealer")
        dealer.drawCard(Card(Rank.TEN, Suit.SPADES)) // 10
        dealer.drawCard(Card(Rank.SEVEN, Suit.CLUBS)) // 7 → 17

        val player = Player("player", Bet(15000))
        player.drawCard(Card(Rank.NINE, Suit.DIAMONDS)) // 9
        player.drawCard(Card(Rank.EIGHT, Suit.HEARTS)) // 8 → 17

        val statsManager = StatsManager(listOf(player), dealer)
        statsManager.processStatistics(listOf(player), dealer)

        assertEquals(EarningsResult.TIE_BET, statsManager.winStatistics.playerBoard[player])
        assertEquals(0, statsManager.earnings[player])
        assertEquals(0, statsManager.earnings[dealer])
    }

    @Test
    fun `player gets Blackjack`() {
        val dealer = Dealer("Dealer")
        dealer.drawCard(Card(Rank.EIGHT, Suit.SPADES)) // 8
        dealer.drawCard(Card(Rank.NINE, Suit.CLUBS)) // 9 → 17

        val player = Player("player", Bet(10000))
        player.drawCard(Card(Rank.ACE, Suit.HEARTS)) // 11
        player.drawCard(Card(Rank.JACK, Suit.SPADES)) // 10 → 21 (Blackjack)

        val statsManager = StatsManager(listOf(player), dealer)
        statsManager.processStatistics(listOf(player), dealer)

        assertEquals(EarningsResult.WIN_BLACK_JACK_BET, statsManager.winStatistics.playerBoard[player])
        assertEquals(15000, statsManager.earnings[player])
        assertEquals(-15000, statsManager.earnings[dealer])
    }

    @Test
    fun `dealer busts and player wins`() {
        val dealer = Dealer("Dealer")
        dealer.drawCard(Card(Rank.NINE, Suit.SPADES)) // 9
        dealer.drawCard(Card(Rank.SEVEN, Suit.CLUBS)) // 7
        dealer.drawCard(Card(Rank.SIX, Suit.HEARTS)) // 6 → 22 (Bust)

        val player = Player("player", Bet(5000))
        player.drawCard(Card(Rank.TEN, Suit.DIAMONDS)) // 10
        player.drawCard(Card(Rank.SIX, Suit.SPADES)) // 6 → 16

        val statsManager = StatsManager(listOf(player), dealer)
        statsManager.processStatistics(listOf(player), dealer)

        assertEquals(EarningsResult.WIN_BET, statsManager.winStatistics.playerBoard[player])
        assertEquals(5000, statsManager.earnings[player])
        assertEquals(-5000, statsManager.earnings[dealer])
    }

    @Test
    fun `player and dealer both have Blackjack - player gets bet back`() {
        val dealer = Dealer("Dealer")
        dealer.drawCard(Card(Rank.ACE, Suit.SPADES))
        dealer.drawCard(Card(Rank.KING, Suit.HEARTS)) // 21 (Blackjack)

        val player = Player("player", Bet(10000))
        player.drawCard(Card(Rank.ACE, Suit.CLUBS))
        player.drawCard(Card(Rank.JACK, Suit.DIAMONDS)) // 21 (Blackjack)

        val statsManager = StatsManager(listOf(player), dealer)
        statsManager.processStatistics(listOf(player), dealer)

        assertEquals(EarningsResult.TIE_BET, statsManager.winStatistics.playerBoard[player])
        assertEquals(0, statsManager.earnings[player])
        assertEquals(0, statsManager.earnings[dealer])
    }

    @Test
    fun `all players bust - dealer wins all bets`() {
        val dealer = Dealer("Dealer")
        dealer.drawCard(Card(Rank.SEVEN, Suit.HEARTS)) // 7
        dealer.drawCard(Card(Rank.NINE, Suit.CLUBS)) // 9 → 16

        val player1 = Player("player", Bet(10000))
        player1.drawCard(Card(Rank.TEN, Suit.DIAMONDS))
        player1.drawCard(Card(Rank.NINE, Suit.SPADES))
        player1.drawCard(Card(Rank.THREE, Suit.CLUBS)) // 22 – Bust

        val player2 = Player("player", Bet(20000))
        player2.drawCard(Card(Rank.KING, Suit.HEARTS))
        player2.drawCard(Card(Rank.QUEEN, Suit.CLUBS))
        player2.drawCard(Card(Rank.TWO, Suit.HEARTS)) // 22 – Bust

        val statsManager = StatsManager(listOf(player1, player2), dealer)
        statsManager.processStatistics(listOf(player1, player2), dealer)

        assertEquals(EarningsResult.LOSE_BET, statsManager.winStatistics.playerBoard[player1])
        assertEquals(EarningsResult.LOSE_BET, statsManager.winStatistics.playerBoard[player2])
        assertEquals(-10000, statsManager.earnings[player1])
        assertEquals(-20000, statsManager.earnings[player2])
        assertEquals(30000, statsManager.earnings[dealer])
    }
}
