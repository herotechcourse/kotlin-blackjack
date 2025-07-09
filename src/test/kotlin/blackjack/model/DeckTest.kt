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
    fun `deck should reduce its size when giving a card`() {
        deck.giveCard()
        assertThat(deck.getCards()).hasSize(51)
    }

    @Test
    fun `should give a single card`() {
        val listOfCards = listOf(deck.giveCard())
        assertThat(listOfCards).hasSize(1)
    }
}
