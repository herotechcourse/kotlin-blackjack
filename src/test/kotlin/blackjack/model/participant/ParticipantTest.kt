package blackjack.model.participant

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `Participants init with player and dealer, and recognize by name`() {
        val playerNames = listOf("mina", "guri", "life", "hard")
        val participants = Participants.from(playerNames)

        Assertions.assertThat( playerNames.all { participants.contain(it)}).isTrue
    }

    @Test
    fun `Participants init with player and dealer, and recognize by type of Participant`() {
        val playerNames = listOf("mina", "guri", "life", "hard")
        val participants = Participants.from(playerNames)
        val dealer = participants.getDealer()

        Assertions.assertThat(participants.contain(dealer)).isTrue()
    }

    @Test
    fun `Participants init with players`() {
        val participants = Participants("mina", "guri")

        Assertions.assertThat(participants.containsAll("mina", "guri")).isTrue()
        Assertions.assertThat(participants.containsAll("guri", "I am not Guri")).isFalse()
        Assertions.assertThat(participants[0].name).isEqualTo("mina")
    }

    @Test
    fun `can recognize number of players`() {
        val participants = Participants("mina", "guri")
        Assertions.assertThat(participants.numberOfPlayers()).isEqualTo(2)
    }
}
