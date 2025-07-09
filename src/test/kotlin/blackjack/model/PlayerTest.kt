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
        assertEquals(0, player.hand.size)

        // draw first card
        player.drawCard(cards[0])
        assertEquals(1, player.hand.size)
        assertEquals(cards[0], player.hand.last())

        // draw second card
        player.drawCard(cards[1])
        assertEquals(2, player.hand.size)
        assertEquals(cards[1], player.hand.last())
    }

    @Test
    fun `Player has hand which is a list of card`() {
        val cards = CardGenerator.generateCards()
        val player = Player("player")

        // initial state of player's hand
        assertEquals(emptyList<Card>(), player.hand)

        // draw first card
        player.drawCard(cards[0])
        assertEquals(cards.subList(0, 1), player.hand)

        // draw second card
        player.drawCard(cards[1])
        assertEquals(cards.subList(0, 2), player.hand)
    }

    @Test
    fun `calculateHand() - player has TWO and TEN`() {
        val player = Player("player")

        player.drawCard(Card("2♦"))
        player.drawCard(Card("10♦"))

        assertEquals(12, player.calculateHand())
    }

    @Test
    fun `calculateHand() - player has Two ACE`() {
        val cards = CardGenerator.generateCards()
        val player = Player("player")

        player.drawCard(cards[0])
        player.drawCard(cards[1])

        assertEquals(12, player.calculateHand())
    }

    @Test
    fun `calculateHand() - player has Three ACE and TWO`() {
        val cards = CardGenerator.generateCards()
        val player = Player("player")

        player.drawCard(cards[0])
        player.drawCard(cards[1])
        player.drawCard(cards[2])
        player.drawCard(Card("2♦"))

        assertEquals(15, player.calculateHand())
    }

    @Test
    fun `calculateHand() - player has Three ACE and TEN`() {
        val cards = CardGenerator.generateCards()
        val player = Player("player")

        player.drawCard(cards[0])
        player.drawCard(cards[1])
        player.drawCard(cards[2])
        player.drawCard(Card("10♦"))

        assertEquals(13, player.calculateHand())
    }

    @Test
    fun `isBust() - player is busted`() {
        val cards = CardGenerator.generateCards()
        val player = Player("player")

        player.drawCard(cards[51])
        player.drawCard(cards[50])
        player.drawCard(cards[49])
        val score = player.calculateHand()
        assertEquals(true, player.isBust())
    }

    @Test
    fun `isBust() - player is not busted`() {
        val cards = CardGenerator.generateCards()
        val player = Player("player")

        player.drawCard(cards[51])
        player.drawCard(cards[50])
        assertEquals(false, player.isBust())
    }
}
