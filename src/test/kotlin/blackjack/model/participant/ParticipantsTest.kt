package blackjack.model.participant

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `Participants init with player and dealer, and recognize by name`() {
        val names = listOf("mina", "guri", "life", "hard")
        val participants = Participants.from(names)

        Assertions.assertThat(names.all { participants.contain(it) }).isTrue
    }

    @Test
    fun `Participants init with player and dealer, and recognize by type of Participant`() {
        val names = listOf("mina", "guri", "life", "hard")
        val participants = Participants.from(names)
        val dealer = participants.getDealer()

        Assertions.assertThat(participants.contain(dealer)).isTrue()
    }

    @Test
    fun `Participants init with players`() {
        val names = listOf("mina", "guri")
        val participants = Participants.from(names)

        Assertions.assertThat(participants.containsAll("mina", "guri")).isTrue()
        Assertions.assertThat(participants.containsAll("guri", "I am not Guri")).isFalse()
        Assertions.assertThat(participants.getPlayer(0).name).isEqualTo("mina")
    }

    @Test
    fun `can recognize number of players`() {
        val names = listOf("mina", "guri")
        val participants = Participants.from(names)

        Assertions.assertThat(participants.getPlayers().size).isEqualTo(2)
    }
}
