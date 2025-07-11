package blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CardGeneratorTest {
    @Test
    fun `generate cards with suit`() {
        val generator = CardGenerator()
        val cardsAmount = generator.generate().count()
        assertEquals(48, cardsAmount, "Cards amount aren't 48",)
    }


}