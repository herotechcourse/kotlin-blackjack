package blackjack.model.state

import blackjack.DummyPlayerFactory
import blackjack.TestFixture.DoesNotHasAce.TOTAL_SUM_16
import blackjack.TestFixture.HasAce.TOTAL_BLACKJACK
import blackjack.model.participant.Participants
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StateTest {
    @Test
    fun `dealer is blackjack, player1 also blackJack, and player2 is LOSE`() {
        val factory = DummyPlayerFactory(2)
        val participants = Participants(factory.players)

        val dealer = participants.dealer
        val player1 = participants.players[0]
        val player2 = participants.players[1]

        dealer.receive(TOTAL_BLACKJACK)
        player1.receive(TOTAL_BLACKJACK)
        player2.receive(TOTAL_SUM_16)

        assertThat(dealer.state).isEqualTo(State.BLACKJACK)
        assertThat(player1.state).isEqualTo(State.BLACKJACK)
        assertThat(player2.state).isEqualTo(State.HIT)
    }
}
