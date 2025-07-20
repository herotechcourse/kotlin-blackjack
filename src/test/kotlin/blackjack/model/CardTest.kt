package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `should return the rank value and the suit symbol toString()`() {
        val card = Card(suit = Suit.HEARTS, rank = Rank.ACE)
        assertThat(card.toString()).isEqualTo("${card.rank.symbol}${card.suit.symbol}")
    }

    @Test
    fun `rank values should be correct`() {
        assertThat(Rank.ACE.value).isEqualTo(1)
        assertThat(Rank.TWO.value).isEqualTo(2)
        assertThat(Rank.TEN.value).isEqualTo(10)
        assertThat(Rank.JACK.value).isEqualTo(10)
        assertThat(Rank.QUEEN.value).isEqualTo(10)
        assertThat(Rank.KING.value).isEqualTo(10)
    }
}
