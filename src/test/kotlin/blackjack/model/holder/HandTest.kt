package blackjack.model.holder

import blackjack.TestFixture
import blackjack.TestFixture.first
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HandTest {
    @Test
    fun `init Hand`() {
        Hand()
    }

    @Test
    fun `throw if hand is empty, but try to draw`() {
        val emptyHand = Hand()
        assertThrows<IllegalStateException> {
            emptyHand.draw(1)
        }
    }

    @Test
    fun `CardHolder_Hand receive card`() {
        val hand = Hand()
        val card = TestFixture.Card.HEART_ACE

        hand.receive(listOf(card))
        assertEquals(card, hand.first())
    }

    @Test
    fun `CardHolder_Hand draw card`() {
        val hand = Hand()
        val card = TestFixture.Card.HEART_ACE

        hand.receive(listOf(card))
        val drawCard = hand.draw(1)
        assertEquals(drawCard[0], card)
    }
}
