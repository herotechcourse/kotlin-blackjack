package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerFactoryTest {
    @Test
    fun with() {
        val names = listOf("mina", "vito")
        val players = PlayerFactory.with(names)
        assertThat(players).hasSize(2)
    }
}
