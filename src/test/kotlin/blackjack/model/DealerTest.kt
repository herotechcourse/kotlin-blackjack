package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `should able to draw a card if the total value of cards is equal or less than 16`() {
        val totalValueOfCards = 14
        val dealer = Dealer()
        assertThat(dealer.mustDraw(totalValueOfCards)).isTrue
    }
}
