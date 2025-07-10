package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `calculateCards() - calculate score of cards`() {
        val cards = listOf(Card("Q♦"), Card("K♦"), Card("J♠"))
        val hand = Hand(cards)
        val result = hand.calculateCards()
        assertEquals(30, result)
    }

    @Test
    fun `toText() - return card names with ', '`() {
        val cards = listOf(Card("Q♦"), Card("K♦"), Card("J♠"))
        val expected = "Q♦, K♦, J♠"
        val hand = Hand(cards)
        val result = hand.toText()
        assertEquals(expected, result)
    }
}
