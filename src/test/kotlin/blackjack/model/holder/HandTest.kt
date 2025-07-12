package blackjack.model.holder

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
            emptyHand.drawCard()
        }
    }
}