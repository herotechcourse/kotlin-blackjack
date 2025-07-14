package blackjack

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue

class HandTest {
    private lateinit var hand: Hand

    @BeforeEach
    fun setUp() {
        hand = Hand()
    }

    @Test
    fun `should calculate total correctly without aces`() {
        hand.add(Card(Suit.HEART, Rank.EIGHT))
        hand.add(Card(Suit.CLUB, Rank.NINE))
        assertEquals(17, hand.getScore())
    }

    @Test
    fun `should count ace as 11 when total stays under 21`() {
        hand.add(Card(Suit.SPADE, Rank.ACE))
        hand.add(Card(Suit.DIAMOND, Rank.SEVEN))
        assertEquals(18, hand.getScore())
    }

    @Test
    fun `should detect hand as busted when score over 21`() {
        hand.add(Card(Suit.HEART, Rank.KING))
        hand.add(Card(Suit.CLUB, Rank.QUEEN))
        hand.add(Card(Suit.SPADE, Rank.TWO))
        assertTrue(hand.isBusted())
    }
}
