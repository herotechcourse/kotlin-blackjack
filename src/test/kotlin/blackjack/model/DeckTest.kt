package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    // test dropLastCard
    @Test
    fun `it should initialize a deck with a list of cards with the size 52`() {
        val deck = Deck.generateADeck()
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `it should return a card`() {
        val deck = Deck.generateADeck()
        assertThat(deck.drawCard()).isInstanceOf(Card::class.java)
    }
}
