package blackjack.model.holder

import blackjack.model.TestFixture
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
            emptyHand.draw()
        }
    }

    @Test
    fun `CardHolder_Hand receive card`() {
        val hand = Hand()
        val card = TestFixture.Card.HEART_ACE

        hand.receive(card)
       assertEquals(card, hand.peek())
    }

    @Test
    fun `CardHolder_Hand draw card`() {
        val hand = Hand()
        val card = TestFixture.Card.HEART_ACE

        hand.receive(card)
        val drawCard = hand.draw()
        assertEquals(drawCard, card)

    }
}
