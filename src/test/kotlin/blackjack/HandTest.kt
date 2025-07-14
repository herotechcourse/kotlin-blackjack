package blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `given card values returns correct sum`() {
        val hand = Hand()
        hand.addCard(Card(CardSuit.DIAMOND, 2))
        hand.addCard(Card(CardSuit.SPADE, 10))
        val sum = hand.sumCards()
        assertEquals(12, sum, "The sumCard method should return the sum of cards")
    }
}