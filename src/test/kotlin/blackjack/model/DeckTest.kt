package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DeckTest {
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck = Deck()
    }

    @Test
    fun `should start with 52 cards`() {
        assertEquals(52, deck.remainingCards())
    }

    @Test
    fun `should draw one card and decrease deck size`() {
        deck.drawCard()
        assertEquals(51, deck.remainingCards())
    }

    @Test
    fun `should throw error when drawing from empty deck`() {
        repeat(52) { deck.drawCard() }
        assertThrows(IllegalStateException::class.java) {
            deck.drawCard()
        }
    }
}
