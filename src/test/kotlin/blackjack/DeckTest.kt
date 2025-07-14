package blackjack

import blackjack.model.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `returns 52 - size of the original deck`() {
        val deck = Deck()
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `returns 51 - size of the original deck after removeCards(1)`() {
        val deck = Deck()
        deck.drawCards()
        assertThat(deck.cards.size).isEqualTo(51)
    }

    @Test
    fun `returns 50 - size of the original deck after removeCards(2)`() {
        val deck = Deck()
        deck.drawCards(2)
        assertThat(deck.cards.size).isEqualTo(50)
    }

    @Test
    fun `returns false - Card removed from the List`() {
        val deck = Deck()
        val card = deck.drawCards().first()

        assertThat(deck.cards.contains(card)).isFalse()
    }

    @Test
    fun `Generated new cards if one card left`() {
        val deck = Deck()
        deck.drawCards(51)
        deck.drawCards()

        assertThat(deck.cards.size).isEqualTo(51)
    }
}
