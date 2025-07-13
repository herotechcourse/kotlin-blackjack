package blackjack.controller

import blackjack.TestFixture
import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.card.Suit
import blackjack.model.holder.Deck
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

        player.receive(Card(Suit.HEART, Rank.ACE))
        player.receive(Card(Suit.SPADE, Rank.KING))

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

    @Test
    fun `round works correctly with Player Participant`() {
        val participants = Participants.from(listOf("test"))
        val gameManager = GameManager(participants)

        val player = gameManager.getPlayers().first()
        val cards = TestFixture.BustedWithLastCard.TOTAL_SUM_WAS_20.toMutableList()

        gameManager.injectTestDeck(cards)

        player.receive(cards.draw())
        player.receive(cards.draw())

        val prevScore = player.score

        gameManager.round(player) { true }
        assertThat(prevScore).isNotEqualTo(player.score)
    }

    @Test
    fun `round works correctly with Player Participant - not busted, `() {
        val participants = Participants.from(listOf("test"))
        val gameManager = GameManager(participants)

        val player = gameManager.getPlayers().first()
        val cards = TestFixture.DoesNotHasAce.EXACTLY_21_FIVE_CARDS.toMutableList()

        gameManager.injectTestDeck(cards)

        val expect = cards.sumOf { it.rank.value }

        gameManager.playPlayerRound(player) { true }
        assertThat(player.score).isEqualTo(expect)
    }

    @Test
    fun `round works correctly with Dealer Participant`() {
        val participants = Participants.from(listOf("test"))
        val gameManager = GameManager(participants)

        val dealer = gameManager.getDealer()
        val cards = TestFixture.BustedWithLastCard.TOTAL_SUM_WAS_20.toMutableList()
        gameManager.injectTestDeck(cards)

        dealer.receive(cards.draw())
        dealer.receive(cards.draw())

        val prevScore = dealer.score

        gameManager.round(dealer) { true }
        assertThat(prevScore).isNotEqualTo(dealer.score)
    }

    private fun GameManager.injectTestDeck(cards: List<Card>) {
        repeat(Deck.FULL_DECK_SIZE - 1) { getDeck().draw() }
        cards.forEach { getDeck().receive(it) }
        getDeck().draw()
    }

    private fun MutableList<Card>.draw(): Card {
        return this.removeFirst()
    }
}
