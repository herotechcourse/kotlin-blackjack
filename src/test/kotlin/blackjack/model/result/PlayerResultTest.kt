package blackjack.model.result

import blackjack.DummyPlayerFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerResultTest {
    @Test
    fun `final amount is -10000 if player bet 10000 and Lose`() {
        val player = DummyPlayerFactory(1).players[0]
        val playerResult = PlayerResult(player, Outcome.LOSE)

        assertThat(playerResult.finalAmount).isEqualTo(-10000)
    }

    @Test
    fun `final amount is 20000 if player bet 10000 and Win`() {
        val player = DummyPlayerFactory(1).players[0]
        val playerResult = PlayerResult(player, Outcome.WIN)

        assertThat(playerResult.finalAmount).isEqualTo(20000)
    }

    @Test
    fun `final amount is 25000 if player bet 10000 and Blackjack`() {
        val player = DummyPlayerFactory(1).players[0]
        val playerResult = PlayerResult(player, Outcome.BLACKJACK)

        assertThat(playerResult.finalAmount).isEqualTo(25000)
    }

    @Test
    fun `final amount is 10000 if player bet 10000 and draw`() {
        val player = DummyPlayerFactory(1).players[0]
        val playerResult = PlayerResult(player, Outcome.DRAW)

        assertThat(playerResult.finalAmount).isEqualTo(10000)
    }
}
