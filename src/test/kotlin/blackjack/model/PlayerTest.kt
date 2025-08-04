package blackjack.model

import blackjack.Fixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest(name = "Player - has name {0}")
    @ValueSource(strings = ["Player", "A♥", "san", "ann"])
    fun `Player has a name in string`(candidate: String) {
        assertEquals(candidate, Player(candidate).name)
    }

    @Test
    fun `Player init with hand of two cards`() {
        val player = Player("player")

        assertEquals(Hand.INIT_CARDS_SIZE, player.hand.cards.size)
    }

    @Test
    fun `Player has hand which is a list of card`() {
        val deck = PlayingCard.deck

        // init new player with first card
        val firstCard = deck.giveCard()
        val hand = Hand(listOf(firstCard))
        val player = Player("new player", hand = hand)
        assertEquals(firstCard, player.hand.cards.last())

        // draw second card
        val secondCard = deck.giveCard()
        player.drawCard(secondCard)
        assertEquals(secondCard, player.hand.cards.last())
    }

    @Test
    fun `Player can init with hand having a card`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_SEVEN))
        val player = Player("player", hand = hand)

        assertEquals(1, player.hand.cards.size)
    }

    @Test
    fun `requestCard() - request true`() {
        val player = Player("player")
        val request = player.requestCard { true }
        assertEquals(true, request)
    }

    @Test
    fun `requestCard() - request false`() {
        val player = Player("player")
        val request = player.requestCard { false }
        assertEquals(false, request)
    }

    @Test
    fun `drawCard()`() {
        val hand = Hand(listOf(Fixture.SPADES_ACE))
        val player = Player("player", hand = hand)

        // draw first card
        assertEquals(1, player.hand.cards.size)
        assertEquals(Fixture.SPADES_ACE, player.hand.cards.last())

        // draw second card
        player.drawCard(Fixture.DIAMONDS_JACK)
        assertEquals(2, player.hand.cards.size)
        assertEquals(Fixture.DIAMONDS_JACK, player.hand.cards.last())
    }

    @Test
    fun `calculateHand() - player has TWO and TEN`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_TWO, Fixture.DIAMONDS_TEN))
        val player = Player("player", hand = hand)

        assertEquals(12, player.hand.calculateHand())
    }

    @Test
    fun `calculateHand() - player has Two ACE`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_ACE, Fixture.SPADES_ACE))
        val player = Player("player", hand = hand)

        assertEquals(12, player.hand.calculateHand())
    }

    @Test
    fun `calculateHand() - player has Three ACE and TWO`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_ACE, Fixture.SPADES_ACE, Fixture.DIAMONDS_TWO, Fixture.HEARTS_ACE))
        val player = Player("player", hand = hand)

        assertEquals(15, player.hand.calculateHand())
    }

    @Test
    fun `calculateHand() - player has Three ACE and TEN`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_ACE, Fixture.SPADES_ACE, Fixture.DIAMONDS_TEN, Fixture.HEARTS_ACE))
        val player = Player("player", hand = hand)

        assertEquals(13, player.hand.calculateHand())
    }

    @Test
    fun `isBust() - player is busted`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_JACK, Fixture.DIAMONDS_QUEEN))
        val player = Player("player", hand = hand)

        assertEquals(true, player.hand.isBust)
    }

    @Test
    fun `isBust() - player is not busted`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_JACK))
        val player = Player("player", hand = hand)

        assertEquals(false, player.hand.isBust)
    }

    @Test
    fun `placeBets() - player place bet`() {
        val player = Player("player", 100)
        assertEquals(100, player.bet)
    }
}
