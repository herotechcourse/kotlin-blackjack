package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class CardTest {
    @Test
    fun `should return same instance for same card`() {
        val suit = Suit.HEART
        val rank = Rank.ACE

        val card1 = Card.of(suit, rank)
        val card2 = Card.of(suit, rank)

        assertThat(card1).isSameAs(card2)
        assertThat(card1).isEqualTo(card2)
    }

    @Test
    fun `should have exactly 52 cards in pool`() {
        val allCards = Card.allCards()

        assertThat(allCards)
            .hasSize(52)
            .doesNotHaveDuplicates()
    }
}
