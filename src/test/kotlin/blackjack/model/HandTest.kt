package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `calculateHand() - calculate score of cards`() {
        val cards = listOf(PlayingCard("Q♦"), PlayingCard("K♦"), PlayingCard("J♠"))
        val hand = Hand(cards)
        val result = hand.calculateHand()
        assertEquals(30, result)
    }
}
