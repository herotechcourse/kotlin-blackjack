package blackjack.model.participant

import blackjack.factory.FakePlayerFactory
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `Participants init with player and dealer, and recognize by name`() {
        val factory = FakePlayerFactory(4)
        val participants = Participants(factory.players)

        Assertions.assertThat(factory.names.all { participants.contains(it) }).isTrue
    }

    @Test
    fun `Participants init with player and dealer, and recognize by type of Participant`() {
        val factory = FakePlayerFactory(4)
        val participants = Participants(factory.players)
        val dealer = participants.dealer

        Assertions.assertThat(participants.contains(dealer)).isTrue()
    }

    @Test
    fun `Participants init with players`() {
        val factory = FakePlayerFactory(2)
        val participants = Participants(factory.players)

        Assertions.assertThat(participants.containsAll(factory.names[0], factory.names[1])).isTrue()
        Assertions.assertThat(participants.containsAll(factory.names[0], "I am not Guri")).isFalse()
        Assertions.assertThat(participants.players[0].name).isEqualTo(factory.names[0])
    }

    @Test
    fun `can recognize number of players`() {
        val factory = FakePlayerFactory(2)
        val participants = Participants(factory.players)

        Assertions.assertThat(participants.players.size).isEqualTo(2)
    }
}
