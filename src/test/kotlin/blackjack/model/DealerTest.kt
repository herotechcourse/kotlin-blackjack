package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        player = Player("TestPlayer")
    }

    @Test
    fun `should give one card to player`() {
        val initialSize = player.getNumberOfCardsInHand()
        dealer.giveCardTo(player)
        val newSize = player.getNumberOfCardsInHand()
        assertEquals(initialSize + 1, newSize)
    }

    @Test
    fun `should draw when dealer score is 16 or less`() {
        dealer.addCard(Card(Suit.HEART, Rank.NINE))
        dealer.addCard(Card(Suit.CLUB, Rank.SEVEN))

        val shouldDraw = dealer.shouldDraw()

        assertTrue(shouldDraw)
    }

    @Test
    fun `should not draw when dealer score is 17 or more`() {
        dealer.addCard(Card(Suit.SPADE, Rank.TEN))
        dealer.addCard(Card(Suit.DIAMOND, Rank.SEVEN))

        val shouldDraw = dealer.shouldDraw()

        assertFalse(shouldDraw)
    }
}
