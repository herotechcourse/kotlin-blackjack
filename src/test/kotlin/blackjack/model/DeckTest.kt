package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `deck should start with 52 cards`() {
        val deck = Deck()
        assertThat(deck.countCards()).isEqualTo(52)
    }

    @Test
    fun `drawing one card reduces deck size by 1`() {
        val deck = Deck()
        val card = deck.draw().first()
        assertThat(card).isNotNull()
        assertThat(deck.countCards()).isEqualTo(51)
    }

    @Test
    fun `drawing multiple cards returns correct number and updates deck size`() {
        val deck = Deck()
        val cards = deck.draw(5)
        assertThat(cards).hasSize(5)
        assertThat(deck.countCards()).isEqualTo(47)
    }

    @Test
    fun `drawing more cards than deck has throws exception`() {
        val deck = Deck()
        assertThrows<IllegalArgumentException> {
            deck.draw(60)
        }
    }

    @Test
    fun `drawing zero or negative number of cards throws exception`() {
        val deck = Deck()
        assertThrows<IllegalArgumentException> {
            deck.draw(0)
        }

        assertThrows<IllegalArgumentException> {
            deck.draw(-3)
        }
    }

    @Test
    fun `drawing a card from an empty deck throws exception`() {
        val deck = Deck()
        repeat(52) { deck.draw() }

        assertThrows<IllegalArgumentException> {
            deck.draw()
        }
    }
}
