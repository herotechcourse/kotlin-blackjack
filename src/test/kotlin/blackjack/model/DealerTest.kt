package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    private class TestPlayer(
        private val score: Int,
        private val busted: Boolean,
        private val blackjack: Boolean,
    ) : Player(name = "Test", bet = Bet.from(1000)) {
        override fun getScore() = score

        override fun isBusted() = busted

        override fun isBlackJack() = blackjack
    }

    private class TestDealer(
        private val score: Int,
        private val busted: Boolean,
        private val blackjack: Boolean,
    ) : Dealer() {
        override fun getScore() = score

        override fun isBusted() = busted

        override fun isBlackJack() = blackjack
    }

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        player = Player("TestPlayer", bet = Bet.from(1000))
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

    @Test
    fun `returns WIN when player has higher score and no one is busted`() {
        val dealer = TestDealer(score = 18, busted = false, blackjack = false)
        val player = TestPlayer(score = 20, busted = false, blackjack = false)

        val result = dealer.judge(player)
        assertEquals(GameResult.WIN, result)
    }

    @Test
    fun `returns LOSE when player is busted`() {
        val dealer = TestDealer(score = 17, busted = false, blackjack = false)
        val player = TestPlayer(score = 22, busted = true, blackjack = false)

        val result = dealer.judge(player)
        assertEquals(GameResult.LOSE, result)
    }

    @Test
    fun `returns DRAW when scores are equal and no one is busted`() {
        val dealer = TestDealer(score = 20, busted = false, blackjack = false)
        val player = TestPlayer(score = 20, busted = false, blackjack = false)

        val result = dealer.judge(player)
        assertEquals(GameResult.DRAW, result)
    }

    @Test
    fun `returns DRAW when both player and dealer have Blackjack`() {
        val dealer = TestDealer(score = 21, busted = false, blackjack = true)
        val player = TestPlayer(score = 21, busted = false, blackjack = true)

        val result = dealer.judge(player)
        assertEquals(GameResult.DRAW, result)
    }
}
