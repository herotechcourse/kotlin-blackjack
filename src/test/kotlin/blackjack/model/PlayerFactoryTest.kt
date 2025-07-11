package blackjack.model

import blackjack.model.participant.PlayerFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerFactoryTest {
    @Test
    fun with() {
        val names = listOf("mina", "vito")
        val players = PlayerFactory.createPlayers(names)
        assertThat(players).hasSize(2)
    }
}
