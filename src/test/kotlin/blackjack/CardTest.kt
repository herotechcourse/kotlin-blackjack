package blackjack

import blackjack.model.Card
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `test card constructor`() {
        val card = Card(Rank.ACE, Suit.DIAMONDS)
        assertThat(card.rank).isEqualTo(Rank.ACE)
        assertThat(card.suit).isEqualTo(Suit.DIAMONDS)
        assertThat(card.rank.title).isEqualTo("A")
    }
}
