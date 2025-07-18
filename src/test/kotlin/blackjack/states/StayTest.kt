package blackjack.states

import blackjack.TestCards
import blackjack.model.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StayTest {
    @Test
    fun `player wins with higher score than dealer`() {
        val playerState = Stay(Hand(listOf(TestCards.QUEEN, TestCards.JACK)))
        val dealerState = Stay(Hand(listOf(TestCards.TWO, TestCards.FOUR)))

        val profit = playerState.profit(dealerState, 1000)
        assertThat(profit).isEqualTo(1000.0)
    }

    @Test
    fun `player lose with lower score than dealer`() {
        val dealerState = Stay(Hand(listOf(TestCards.QUEEN, TestCards.JACK)))
        val playerState = Stay(Hand(listOf(TestCards.TWO, TestCards.FOUR)))

        val profit = playerState.profit(dealerState, 1000)
        assertThat(profit).isEqualTo(-1000.0)
    }

    @Test
    fun `player lose with blackJack of dealer`() {
        val dealerState = Blackjack(Hand(listOf(TestCards.QUEEN, TestCards.Ace)))
        val playerState = Stay(Hand(listOf(TestCards.TWO, TestCards.FOUR)))

        val profit = playerState.profit(dealerState, 1000)
        assertThat(profit).isEqualTo(-1500.0)
    }

    @Test
    fun `draw tie`() {
        val dealerState = Stay(Hand(listOf(TestCards.QUEEN, TestCards.JACK)))
        val playerState = Stay(Hand(listOf(TestCards.QUEEN, TestCards.JACK)))

        val profit = playerState.profit(dealerState, 1000)
        assertThat(profit).isEqualTo(0.0)
    }
}
