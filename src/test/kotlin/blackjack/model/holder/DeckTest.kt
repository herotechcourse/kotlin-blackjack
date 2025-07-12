package blackjack.model.holder

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `empty card deck generate new deck`() {
        val deck = Deck()
        val oldDeck = deck.cards

        repeat(Deck.FULL_DECK_SIZE) { deck.draw() }
        assertThat(deck.cards).isNotEqualTo(oldDeck)
    }
}
