package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `all players have been set bet`() {
        val players = Players(names.map { Player(it) })
        players.setBet { BET }
        assertThat(players.value.all { it.bet == BET }).isTrue()
    }

    companion object {
        val names = listOf("brie", "jason", "justin")
        const val BET = 1000
    }
}
