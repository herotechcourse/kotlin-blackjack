package blackjack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `draws a card`() {
        val deck = Deck.generate()
        Dealer(deck).drawCard()
        val deckSize = deck.cards.size

        assertEquals(47, deckSize, "Should draw a card out of the deck")
    }
}