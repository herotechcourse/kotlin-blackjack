package blackjack.model.result

import blackjack.DummyPlayerFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerEarningTest {
    @Test
    fun `dealer earning 0 with if player bet 10000 and Draw`() {
        val player = DummyPlayerFactory(1).players[0]
        val playerResult = PlayerResult(player, Outcome.DRAW)
        val dealerEarning = DealerEarning(listOf(playerResult)).amount

        assertThat(dealerEarning).isEqualTo(0)
    }

    @Test
    fun `dealer earning 10000 with if player bet 10000 and Lose`() {
        val player = DummyPlayerFactory(1).players[0]
        val playerResult = PlayerResult(player, Outcome.LOSE)
        val dealerEarning = DealerEarning(listOf(playerResult)).amount

        assertThat(dealerEarning).isEqualTo(10000)
    }

    @Test
    fun `dealer earning -10000 with if player bet 10000 and Win`() {
        val player = DummyPlayerFactory(1).players[0]
        val playerResult = PlayerResult(player, Outcome.WIN)
        val dealerEarning = DealerEarning(listOf(playerResult)).amount

        assertThat(dealerEarning).isEqualTo(-10000)
    }

    @Test
    fun `dealer earning -15000 with if player bet 10000 and Blackjack`() {
        val player = DummyPlayerFactory(1).players[0]
        val playerResult = PlayerResult(player, Outcome.BLACKJACK)
        val dealerEarning = DealerEarning(listOf(playerResult)).amount

        assertThat(dealerEarning).isEqualTo(-15000)
    }
}
