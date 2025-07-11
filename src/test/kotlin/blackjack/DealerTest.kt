package blackjack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `first turn takes 2 cards`() {
        val dealer = Dealer()
        val cardGenerator = CardGenerator()
        val cards = dealer.firstTurnCards(cardGenerator.generate())
        assertEquals(cards.size, 2, "First turn should contain 2 cards")
    }

    @Test
    fun `first 2 cards should be different`() {
        val dealer = Dealer()
        val cardGenerator = CardGenerator()
        val cards = dealer.firstTurnCards(cardGenerator.generate())
        assertTrue(cards[0] != cards[1], "Hand should have different cards")
    }
}