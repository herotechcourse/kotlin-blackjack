package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `CardDeck has 52 cards`() {
        val cardDeck = CardDeck()
        assertThat(cardDeck.size).isEqualTo(52)
    }

    @Test
    fun `should contain all unique cards`() {
        val deck = CardDeck()
        val cards = deck.cards

        assertThat(cards)
            .hasSize(52)
            .doesNotHaveDuplicates()
    }

    @Test
    fun `should be shuffled - different order each time`() {
        val deck1 = CardDeck()
        val deck2 = CardDeck()

        assertThat(deck1.cards)
            .containsExactlyInAnyOrderElementsOf(deck2.cards)
            .isNotEqualTo(deck2.cards)
    }

    @Test
    fun `should draw a card`() {
        val cardDeck = CardDeck()
        val card = cardDeck.drawCard()

        assertThat(cardDeck.cards).hasSize(51)
    }
}
