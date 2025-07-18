package blackjack.states

import blackjack.TestCards
import blackjack.model.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackTest {
    @Test
    fun `player wins with blackjack`() {
        val playerState = Blackjack(Hand(listOf(TestCards.QUEEN, TestCards.Ace)))
        val dealerState = Stay(Hand(listOf(TestCards.TWO, TestCards.FOUR)))

        val profit = playerState.profit(dealerState, 1000)
        assertThat(profit).isEqualTo(1500.0)
    }

    @Test
    fun `draw tie two blackjacks`() {
        val dealerState = Blackjack(Hand(listOf(TestCards.QUEEN, TestCards.Ace)))
        val playerState = Blackjack(Hand(listOf(TestCards.QUEEN, TestCards.Ace)))

        val profit = playerState.profit(dealerState, 1000)
        assertThat(profit).isEqualTo(0.0)
    }
}
