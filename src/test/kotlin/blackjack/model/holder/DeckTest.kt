package blackjack.model.holder

import blackjack.model.holder.Deck.Companion.initDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `init deck return sorted 52 cards`() {
        val cards = initDeck()
        assertThat(cards).hasSize(Deck.FULL_DECK_SIZE)

    }

    @Test
    fun `empty card deck generate new deck`() {
        val deck = Deck()
        val oldDeck = deck.cards.toList()

        repeat(Deck.FULL_DECK_SIZE) { deck.draw() }
        assertThat(deck.cards).isNotEqualTo(oldDeck)
    }
}
