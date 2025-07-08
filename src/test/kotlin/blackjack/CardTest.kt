package blackjack

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardTest {
    @Test
    fun `throw if card is illegal`() {
        assertThrows<IllegalArgumentException> {
            Card(Symbol.SPADES, 14)
        }
    }
}
