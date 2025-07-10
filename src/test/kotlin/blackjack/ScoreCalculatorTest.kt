package blackjack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScoreCalculatorTest {

    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("TestPlayer")
    }

    @Test
    fun `should calculate total correctly without aces`() {
        player.addCard(Card(Suit.HEART, Rank.EIGHT))
        player.addCard(Card(Suit.CLUB, Rank.NINE))

        val score = ScoreCalculator.calculate(player)
        assertEquals(17, score)
    }

    @Test
    fun `should count ace as 11 when total stays under 21`() {
        player.addCard(Card(Suit.SPADE, Rank.ACE))
        player.addCard(Card(Suit.DIAMOND, Rank.SEVEN))

        val score = ScoreCalculator.calculate(player)
        assertEquals(18, score)
    }

    @Test
    fun `should detect player as busted when score over 21`() {
        player.addCard(Card(Suit.HEART, Rank.KING))
        player.addCard(Card(Suit.CLUB, Rank.QUEEN))
        player.addCard(Card(Suit.SPADE, Rank.TWO))

        val isBusted = ScoreCalculator.isBusted(player)
        assertTrue(isBusted)
    }
}
