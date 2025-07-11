package blackjack.model

import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import blackjack.model.participant.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `Participants init with player and dealer, and recognize by name`() {
        val name = "mina"
        val participants = Participants(name)

        assertThat(participants.contain(name)).isTrue()
    }

    @Test
    fun `Participants init with player and dealer, and recognize by type of Participant`() {
        val player = Player("name")
        val participants = Participants(player)
        val dealer = Dealer()

        assertThat(participants.contain(dealer)).isTrue()
    }
    @Test
    fun `Participants init with players`() {
        val participants = Participants("mina", "guri")

        assertThat(participants.containsAll("mina", "guri")).isTrue()
        assertThat(participants.containsAll("guri", "I am not Guri")).isFalse()
        assertThat(participants[0].name).isEqualTo("mina")
    }
}