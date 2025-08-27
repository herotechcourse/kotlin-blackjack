package blackjack.model

import blackjack.enum.CardNumber
import blackjack.enum.CardSuit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `given card values returns correct sum`() {
        val hand = Hand()
        hand.addCard(Card(CardSuit.DIAMOND, CardNumber.TWO))
        hand.addCard(Card(CardSuit.SPADE, CardNumber.TEN))
        val sum = hand.sumCards()
        Assertions.assertEquals(12, sum, "The sumCard method should return the sum of cards")
    }
}
