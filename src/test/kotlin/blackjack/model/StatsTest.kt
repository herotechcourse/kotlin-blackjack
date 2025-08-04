package blackjack.model

import blackjack.Fixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StatsTest {
    @Test
    fun `calculateEarningMapForPlayable() - method calculate pay-out for players and dealer`() {
        // player1 -> bust
        val hand1 = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_JACK, Fixture.DIAMONDS_QUEEN))
        val player1 = Player("player1", 100, hand = hand1)

        // player2 -> win
        val hand2 = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_QUEEN))
        val player2 = Player("player2", 200, hand = hand2)

        // player3 -> tie
        val hand3 = Hand(listOf(Fixture.DIAMONDS_NINE, Fixture.DIAMONDS_JACK))
        val player3 = Player("player3", 300, hand = hand3)

        val handOfDealer = Hand(listOf(Fixture.DIAMONDS_NINE, Fixture.DIAMONDS_JACK))
        val dealer = Dealer(hand = handOfDealer)

        val stats = Stats(listOf(player1, player2, player3), dealer)
        val earningMap = stats.calculateEarningMapForPlayable()
        assertEquals(-100, earningMap[player1])
        assertEquals(200, earningMap[player2])
        assertEquals(0, earningMap[player3])
        assertEquals(-100, earningMap[dealer])
    }

    @Test
    fun `calculateEarningMapForPlayable() - a player win with blackjack`() {
        // player1 -> bust
        val hand1 = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_JACK, Fixture.DIAMONDS_QUEEN))
        val player1 = Player("player1", 100, hand = hand1)

        // player2 -> win
        val hand2 = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_ACE))
        val player2 = Player("player2", 200, hand = hand2)

        // player3 -> tie
        val hand3 = Hand(listOf(Fixture.DIAMONDS_NINE, Fixture.DIAMONDS_JACK))
        val player3 = Player("player3", 300, hand = hand3)

        val handOfDealer = Hand(listOf(Fixture.DIAMONDS_NINE, Fixture.DIAMONDS_JACK))
        val dealer = Dealer(hand = handOfDealer)

        val stats = Stats(listOf(player1, player2, player3), dealer)
        val earningMap = stats.calculateEarningMapForPlayable()
        assertEquals(-100, earningMap[player1])
        assertEquals(300, earningMap[player2])
        assertEquals(0, earningMap[player3])
        assertEquals(-200, earningMap[dealer])
    }

    @Test
    fun `calculateEarningMapForPlayable() - dealer win with blackjack`() {
        // player1 -> bust
        val hand1 = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_JACK, Fixture.DIAMONDS_QUEEN))
        val player1 = Player("player1", 100, hand = hand1)

        // player2 -> lost
        val hand2 = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_QUEEN))
        val player2 = Player("player2", 200, hand = hand2)

        // player3 -> tie
        val hand3 = Hand(listOf(Fixture.DIAMONDS_NINE, Fixture.DIAMONDS_JACK))
        val player3 = Player("player3", 300, hand = hand3)

        val handOfDealer = Hand(listOf(Fixture.DIAMONDS_ACE, Fixture.DIAMONDS_JACK))
        val dealer = Dealer(hand = handOfDealer)

        val stats = Stats(listOf(player1, player2, player3), dealer)
        val earningMap = stats.calculateEarningMapForPlayable()
        assertEquals(-100, earningMap[player1])
        assertEquals(-200, earningMap[player2])
        assertEquals(-300, earningMap[player3])
        assertEquals(600, earningMap[dealer])
    }

    @Test
    fun `calculateEarningMapForPlayable() - a player and the dealer are blackjack`() {
        // player1 -> bust
        val hand1 = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_JACK, Fixture.DIAMONDS_QUEEN))
        val player1 = Player("player1", 100, hand = hand1)

        // player2 -> blackjack
        val hand2 = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_ACE))
        val player2 = Player("player2", 200, hand = hand2)

        // player3 -> lost
        val hand3 = Hand(listOf(Fixture.DIAMONDS_NINE, Fixture.DIAMONDS_JACK))
        val player3 = Player("player3", 300, hand = hand3)

        val handOfDealer = Hand(listOf(Fixture.DIAMONDS_JACK, Fixture.DIAMONDS_ACE))
        val dealer = Dealer(hand = handOfDealer)

        val stats = Stats(listOf(player1, player2, player3), dealer)
        val earningMap = stats.calculateEarningMapForPlayable()
        assertEquals(-100, earningMap[player1])
        assertEquals(0, earningMap[player2])
        assertEquals(-300, earningMap[player3])
        assertEquals(400, earningMap[dealer])
    }
}
