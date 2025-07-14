package blackjack.model

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DealerTest {
    @Test
    fun `dealer should have name Dealer`() {
        val dealer = Dealer()
        assertEquals("Dealer", dealer.name)
    }

    @Test
    fun `dealer must draw when total is less than or equal to draw limit`() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.SIX, Suit.CLUBS))) // total = 16
        assertTrue(dealer.mustDraw())
    }

    @Test
    fun `dealer should not draw when total is above draw limit`() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.SEVEN, Suit.CLUBS))) // total = 17
        assertFalse(dealer.mustDraw())
    }

    @Test
    fun `dealer total calculates correctly with aces`() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.SIX, Suit.CLUBS))) // ace=11 + six=6, total =17
        assertEquals(17, dealer.total())

        dealer.drawCard(listOf(Card(Rank.ACE, Suit.DIAMONDS)))
        assertEquals(18, dealer.total()) // ace1=11 + six=6 + ace2=1, total =18
    }
}
