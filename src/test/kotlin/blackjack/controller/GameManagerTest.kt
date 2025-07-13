package blackjack.controller

import blackjack.TestFixture
import blackjack.model.card.Rank
import blackjack.model.card.Suit
import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import blackjack.model.state.State
import blackjack.view.OutputView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun setUp() {
        val participants = Participants.from(listOf("mina", "guri"))
        GameManager(participants).setUp()

        OutputView.showCards(participants.getDealer())
        OutputView.showAllPayersCards(participants)
    }

    @Test
    fun `player state is bust if score over 21`() {
        val player = Player("test")

        val cards = TestFixture.DoesNotHasAce.TOTAL_SUM_25
        cards.forEach { player.receive(it) }

        assertThat(player.state).isEqualTo(State.BUST)
    }

    @Test
    fun `player state is hit if score is under 21`() {
        val player = Player("test")

        val cards = TestFixture.DoesNotHasAce.TOTAL_SUM_16
        cards.forEach { player.receive(it) }

        assertThat(player.state).isEqualTo(State.HIT)
        assertThat(player.score).isEqualTo(16)
    }

    @Test
    fun `player state is blackjack if score is exactly 21 with first two cards`() {
        val player = Player("test")

        player.receive(blackjack.model.card.Card(Suit.HEARTS, Rank.ACE))
        player.receive(blackjack.model.card.Card(Suit.SPADES, Rank.KING))

        assertThat(player.state).isEqualTo(State.BLACKJACK)
        assertThat(player.score).isEqualTo(21)
    }

    @Test
    fun `player state calculation works correctly with different card combinations`() {
        val player = Player("test")

        player.receive(TestFixture.Card.HEART_ACE)
        assertThat(player.state).isEqualTo(State.HIT)

        player.receive(TestFixture.Card.DIAMOND_JACK)
        assertThat(player.state).isEqualTo(State.BLACKJACK)

        val canReceive = player.receive(TestFixture.Card.DIAMOND_JACK)
        assertThat(canReceive).isEqualTo(false)
        assertThat(player.state).isEqualTo(State.BLACKJACK)
    }
}
