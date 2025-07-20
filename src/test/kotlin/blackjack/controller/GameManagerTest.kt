package blackjack.controller

import blackjack.DummyPlayerFactory
import blackjack.TestFixture
import blackjack.TestFixture.DoesNotHasAce.FIVE_CARDS_SUM_21
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
        val participants = Participants(DummyPlayerFactory(2).players)
        GameManager(participants).setUp()

        OutputView.showCards(participants.dealer)
        OutputView.showAllPayersCards(participants.players)
    }

    @Test
    fun `player state is bust if score over 21`() {
        val player = Player("test")
        val cards = TestFixture.DoesNotHasAce.FOUR_CARDS_SUM_25

        player.receive(cards)

        assertThat(player.state).isEqualTo(State.BUST)
    }

    @Test
    fun `player state is hit if score is under 21`() {
        val player = Player("test")
        val cards = TestFixture.DoesNotHasAce.THREE_CARDS_SUM_16

        player.receive(cards)

        assertThat(player.state).isEqualTo(State.HIT)
        assertThat(player.score).isEqualTo(16)
    }

    @Test
    fun `player state is blackjack if score is exactly 21 with first two cards`() {
        val player = Player("test")

        player.receive(listOf(Card(Suit.HEART, Rank.ACE)))
        player.receive(listOf(Card(Suit.SPADE, Rank.KING)))

        assertThat(player.state).isEqualTo(State.BLACKJACK)
        assertThat(player.score).isEqualTo(21)
    }

    @Test
    fun `player state calculation works correctly with different card combinations`() {
        val player = Player("test")

        player.receive(listOf(TestFixture.Card.HEART_ACE))
        assertThat(player.state).isEqualTo(State.HIT)

        player.receive(listOf(TestFixture.Card.DIAMOND_JACK))
        assertThat(player.state).isEqualTo(State.BLACKJACK)

        val canReceive = player.receive(listOf(TestFixture.Card.DIAMOND_JACK))
        assertThat(canReceive).isEqualTo(false)
        assertThat(player.state).isEqualTo(State.BLACKJACK)
    }

    @Test
    fun `round works correctly with Player Participant`() {
        val participants = Participants(DummyPlayerFactory(2).players)
        val gameManager = GameManager(participants)

        val player = gameManager.getPlayers().first()
        val cards = TestFixture.BustedWithLastCard.THREE_CARDS_SUM_24.toMutableList()

        gameManager.injectTestDeck(cards)

        player.receive(cards.draw(2))

        val prevScore = player.score

        gameManager.round(player) { true }
        assertThat(prevScore).isNotEqualTo(player.score)
    }

    @Test
    fun `round works correctly with Player Participant - not busted, `() {
        val participants = Participants(DummyPlayerFactory(1).players)
        val gameManager = GameManager(participants)

        val player = gameManager.getPlayers().first()
        val cards = FIVE_CARDS_SUM_21.toMutableList()

        gameManager.injectTestDeck(cards)
        assertThat(gameManager.getDeck().cards).hasSize(cards.size)

        gameManager.playPlayerRound(player) { true }
        assertThat(player.score).isEqualTo(21)
    }

    @Test
    fun `round works correctly with Dealer Participant`() {
        val participants = Participants(DummyPlayerFactory(3).players)
        val gameManager = GameManager(participants)

        val dealer = gameManager.getDealer()
        val cards = TestFixture.BustedWithLastCard.THREE_CARDS_SUM_24.toMutableList()
        gameManager.injectTestDeck(cards)

        dealer.receive(cards.draw(2))

        val prevScore = dealer.score

        gameManager.round(dealer) { true }
        assertThat(prevScore).isNotEqualTo(dealer.score)
    }

    private fun GameManager.injectTestDeck(cards: List<Card>) {
        getDeck().draw(Deck.FULL_DECK_SIZE - 1)
        getDeck().receive(cards)
        getDeck().draw(1)
    }

    private fun MutableList<Card>.draw(count: Int): List<Card> {
        val cards = this.take(count)
        repeat(count) { this.removeFirst() }
        return cards
    }
}
