package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `it should initialize a deck with a list of cards with the size 52`() {
        val deck = Deck()
        assertThat(deck.size).isEqualTo(52)
    }

    @Test
    fun `it should return a card instance`() {
        val deck = Deck()
        assertThat(deck.drawCard()).isInstanceOf(Card::class.java)
    }

    @Test
    fun `the deck should decrease by 1 after each drawCard`() {
        val deck = Deck()
        deck.drawCard()
        assertThat(deck.size).isEqualTo(51)
    }

    @Test
    fun `the deck should be empty after all drawCard`() {
        val deck = Deck()
        repeat(52) { deck.drawCard() }
        assertThat(deck.size).isEqualTo(0)
    }

    @Test
    fun `drawCard should return different cards when drawn multiple times`() {
        val deck = Deck()
        val card1 = deck.drawCard()
        val card2 = deck.drawCard()
        val card3 = deck.drawCard()

        assertThat(card1).isNotEqualTo(card2)
        assertThat(card2).isNotEqualTo(card3)
        assertThat(card1).isNotEqualTo(card3)
        assertThat(deck.size).isEqualTo(49)
    }
}
