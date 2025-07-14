package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `should create a card with given rank and suit`() {
        val card = Card(Rank.TWO, Suit.HEARTS)
        assertThat(card.rank).isEqualTo(Rank.TWO)
        assertThat(card.suit).isEqualTo(Suit.HEARTS)
    }

    @Test
    fun `toString should return correct card format`() {
        val card = Card(Rank.KING, Suit.SPADES)
        assertThat(card.toString()).isEqualTo("KING of SPADES")
    }

    @Test
    fun `cards with same rank and suit should be equal`() {
        val card1 = Card(Rank.ACE, Suit.DIAMONDS)
        val card2 = Card(Rank.ACE, Suit.DIAMONDS)
        assertThat(card1).isEqualTo(card2)
    }
}
