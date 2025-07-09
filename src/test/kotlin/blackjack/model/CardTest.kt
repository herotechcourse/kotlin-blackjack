package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class CardTest {
    @Test
    fun `should create a card with given rank and suit`() {
        val card = Card(Rank.TWO, Suit.HEARTS)
        assertThat(card.rank.toString()).isEqualTo("TWO")
        assertThat(card.suit.toString()).isEqualTo("HEARTS")
    }

    @Test
    fun `should change the visibility of the card`() {
        val card = Card(Rank.TWO, Suit.HEARTS)
        assertThat(card.visibility).isEqualTo(false)
        card.changeVisibilty()
        assertThat(card.visibility).isEqualTo(true)
    }
}
