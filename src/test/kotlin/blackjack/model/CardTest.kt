package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `should return the rank value and the suit symbol toString()`() {
       val card = Card(Card.Suit.DIAMONDS, Card.Rank.TEN)
        assertThat(card.toString()).isEqualTo("${card.rank.value}${card.suit.symbol}")
    }
}
