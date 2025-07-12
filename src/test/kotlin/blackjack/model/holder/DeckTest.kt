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
    fun `empty deck generate new deck`() {
        val deck = Deck()
        val oldDeck = deck.cards

        repeat(Deck.FULL_DECK_SIZE + 1) { deck.draw() }
        assertThat(deck.size()).isEqualTo(Deck.FULL_DECK_SIZE - 1)
        assertThat(deck.cards).isNotSameAs(oldDeck)
    }
}
