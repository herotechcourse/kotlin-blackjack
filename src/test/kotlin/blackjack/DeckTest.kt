package blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `generate cards with suit`() {
        val deck = Deck.generate()
        val cardsAmount = deck.cards.size
        assertEquals(48, cardsAmount, "Cards amount aren't 48",)
    }
}