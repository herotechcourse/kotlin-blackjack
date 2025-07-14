package blackjack.states

import blackjack.TestCards
import blackjack.model.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BustTest {
    @Test
    fun `lose with a bust`() {
        val playerState = Bust(Hand(listOf(TestCards.QUEEN, TestCards.Ace, TestCards.FOUR)))
        val dealerState = Blackjack(Hand(listOf(TestCards.QUEEN, TestCards.Ace)))

        val profit = playerState.profit(dealerState, 1000)
        assertThat(profit).isEqualTo(-1000.0)
    }
}
