package blackjack.model

import blackjack.utils.CardGenerator
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
        val cards = CardGenerator.generateCards()
        val player = Player("player")

        // initial state of player's hand
        assertEquals(0, player.hand.cards.size)

        // draw first card
        player.drawCard(cards[0])
        assertEquals(1, player.hand.cards.size)
        assertEquals(cards[0], player.hand.cards.last())

        // draw second card
        player.drawCard(cards[1])
        assertEquals(2, player.hand.cards.size)
        assertEquals(cards[1], player.hand.cards.last())
    }

    @Test
    fun `Player has hand which is a list of card`() {
        val cards = CardGenerator.generateCards()
        val player = Player("player")

        // initial state of player's hand
        assertEquals(emptyList<Card>(), player.hand.cards)

        // draw first card
        player.drawCard(cards[0])
        assertEquals(cards.subList(0, 1), player.hand.cards)

        // draw second card
        player.drawCard(cards[1])
        assertEquals(cards.subList(0, 2), player.hand.cards)
    }

    @Test
    fun `calculateHand() - player has TWO and TEN`() {
        val player = Player("player")

        player.drawCard(Card(Rank.TWO, Suit.DIAMONDS))
        player.drawCard(Card(Rank.TEN, Suit.DIAMONDS))

        assertEquals(12, player.calculateHand())
    }

    @Test
    fun `calculateHand() - player has Two ACE`() {
        val player = Player("player")

        player.drawCard(Card(Rank.ACE, Suit.DIAMONDS))
        player.drawCard(Card(Rank.ACE, Suit.SPADES))

        assertEquals(12, player.calculateHand())
    }

    @Test
    fun `calculateHand() - player has Three ACE and TWO`() {
        val player = Player("player")

        player.drawCard(Card(Rank.ACE, Suit.DIAMONDS))
        player.drawCard(Card(Rank.ACE, Suit.SPADES))
        player.drawCard(Card(Rank.ACE, Suit.HEARTS))
        player.drawCard(Card(Rank.TWO, Suit.DIAMONDS))

        assertEquals(15, player.calculateHand())
    }

    @Test
    fun `calculateHand() - player has Three ACE and TEN`() {
        val player = Player("player")

        player.drawCard(Card(Rank.ACE, Suit.DIAMONDS))
        player.drawCard(Card(Rank.ACE, Suit.SPADES))
        player.drawCard(Card(Rank.ACE, Suit.HEARTS))
        player.drawCard(Card(Rank.TEN, Suit.DIAMONDS))

        assertEquals(13, player.calculateHand())
    }

    @Test
    fun `isBust() - player is busted`() {
        val player = Player("player")

        player.drawCard(Card(Rank.TEN, Suit.DIAMONDS))
        player.drawCard(Card(Rank.KING, Suit.DIAMONDS))
        player.drawCard(Card(Rank.NINE, Suit.DIAMONDS))
        assertEquals(true, player.isBust())
    }

    @Test
    fun `isBust() - player is not busted`() {
        val player = Player("player")

        player.drawCard(Card(Rank.TEN, Suit.DIAMONDS))
        player.drawCard(Card(Rank.TEN, Suit.SPADES))
        assertEquals(false, player.isBust())
    }
}
