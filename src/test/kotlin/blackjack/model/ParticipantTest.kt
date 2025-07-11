package blackjack.model

import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import blackjack.model.participant.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    fun `Participants create players and dealer, and recognize by name`() {
        val name = "mina"
        val participants = Participants(name)

        assertThat(participants.contain(name)).isTrue()
    }

    @Test

    fun `Participants create players and dealer, and recognize by type of Participant`() {
        val player = Player("name")
        val participants = Participants(player)
        val dealer = Dealer()

        assertThat(participants.contain(dealer)).isTrue()
    }
}