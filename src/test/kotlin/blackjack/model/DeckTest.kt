package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DeckTest {
    val deck = Deck()

    @Test
    fun `deck should contain 52 cards`() {
        assertThat(deck.getCards().count()).isEqualTo(52)
    }

    @Test
    fun `deck should reduce its size when drawing a card`() {
        deck.draw()
        assertThat(deck.getCards()).hasSize(51)
    }

    @Test
    fun `draw should give a single card`() {
        val listOfCards = listOf(deck.draw())
        assertThat(listOfCards).hasSize(1)
    }
}
