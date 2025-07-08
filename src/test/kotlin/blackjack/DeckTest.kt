package blackjack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `deck contains 52 unique cards when created`() {
        val deck = Deck()
        val drawnCards = mutableSetOf<Card>()

        repeat(52) {
            drawnCards.add(deck.draw())
        }

        assertEquals(52, drawnCards.size)
    }

    @Test
    fun `drawing all cards and then one more throws exception`() {
        val deck = Deck()

        repeat(52) {
            deck.draw() // Draw 52 cards
        }

        assertThrows(IllegalStateException::class.java) {
            deck.draw() // 53rd card should fail
        }
    }
}
