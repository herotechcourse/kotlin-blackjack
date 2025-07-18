package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsFactoryTest {
    @Test
    fun generatePlayers() {
        val playersNames = listOf("Player 1", "Player 2")
        val players = ParticipantsFactory.generatePlayers(playersNames)
        assertThat(players).hasSize(2)
        assertThat(players[0].name).isEqualTo("Player 1")
    }

    @Test
    fun generateDealer() {
        val dealer = ParticipantsFactory.generateDealer()
        assertThat(dealer).isInstanceOf(Dealer::class.java)
    }
}
