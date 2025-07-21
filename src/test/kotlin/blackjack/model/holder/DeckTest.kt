package blackjack.model.holder

import blackjack.DummyDeck
import blackjack.model.holder.Deck.Companion.initDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `init deck return sorted 52 cards`() {
        val cards = initDeck()
        assertThat(cards).hasSize(Deck.FULL_DECK_SIZE)
    }

    @Test
    fun `throw if deck is empty but try to draw`() {
        val deck = Deck()

        assertThrows<IllegalStateException> {
            deck.draw(Deck.FULL_DECK_SIZE + 1)
        }
    }

    @Test
    fun `does not throw if deck is shuffled`() {
        val shuffledDeck = DummyDeck.initShuffledDeck()
        val notShuffledDeck = DummyDeck.initNotShuffledDeck()

        assertThat(shuffledDeck.size).isEqualTo(notShuffledDeck.size)
        assertThat(shuffledDeck).hasSize(Deck.FULL_DECK_SIZE)
        assertThat(shuffledDeck).isNotEqualTo(notShuffledDeck.reversed())
        assertThat(shuffledDeck).isNotEqualTo(notShuffledDeck)
    }
}
