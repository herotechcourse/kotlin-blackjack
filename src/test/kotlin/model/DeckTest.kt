package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `pop removes a card from deck`() {
        val deck = Deck()
        val card = deck.pop()
        assertThat(DECK_SIZE).isEqualTo(deck.getCards().size + 1)
        assertThat(card).isNotNull()
    }

    @Test
    fun `if the deck is empty should thrown`() {
        val deck = Deck()
        val exception =
            assertThrows<IllegalArgumentException> {
                repeat(DECK_SIZE + 1) {
                    deck.pop()
                }
            }
        assertThat(exception).hasMessage("The deck is empty")
    }

    private companion object {
        const val DECK_SIZE = 52
    }
}
