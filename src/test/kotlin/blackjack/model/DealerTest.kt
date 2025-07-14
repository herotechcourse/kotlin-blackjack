package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DealerTest {
    @Test
    fun `dealer should have name Dealer`() {
        val dealer = Dealer()
        assertEquals("Dealer", dealer.name)
    }

    @Test
    fun `dealer must draw when total is less than or equal to draw limit`() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.SIX, Suit.CLUBS))) // total = 16
        assertTrue(dealer.mustDraw())
    }

    @Test
    fun `dealer should not draw when total is above draw limit`() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.SEVEN, Suit.CLUBS))) // total = 17
        assertFalse(dealer.mustDraw())
    }

    @Test
    fun `dealer total calculates correctly with aces`() {
        val dealer = Dealer()
        dealer.drawCard(listOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.SIX, Suit.CLUBS))) // ace=11 + six=6, total =17
        assertEquals(17, dealer.total())

        dealer.drawCard(listOf(Card(Rank.ACE, Suit.DIAMONDS)))
        assertEquals(18, dealer.total()) // ace1=11 + six=6 + ace2=1, total =18
    }

    @Test
    fun `should return positive amount when players lose`() {
        val dealer = Dealer()
        val player = Player("pobi", 10000)

        // simulate dealer win: dealer has higher total
        dealer.drawCard(listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.NINE, Suit.CLUBS))) // 19
        player.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.EIGHT, Suit.DIAMONDS))) // 18

        player.updateWinningMoney(dealer)

        val result = dealer.returnWinningMoneyForDealer(listOf(player))
        assertThat(result).isEqualTo(10000) // Dealer earns 1 player's loss
    }

    @Test
    fun `should return negative amount when player wins`() {
        val dealer = Dealer()
        val player = Player("jason", 10000)

        dealer.drawCard(listOf(Card(Rank.FIVE, Suit.SPADES), Card(Rank.SIX, Suit.CLUBS))) // 11
        player.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.KING, Suit.DIAMONDS))) // 20

        player.updateWinningMoney(dealer)

        val result = dealer.returnWinningMoneyForDealer(listOf(player))
        assertThat(result).isEqualTo(-10000) // Dealer pays 1 player's win
    }

    @Test
    fun `should return 0 when tie`() {
        val dealer = Dealer()
        val player = Player("alex", 10000)

        dealer.drawCard(listOf(Card(Rank.NINE, Suit.SPADES), Card(Rank.TWO, Suit.HEARTS))) // 11
        player.drawCard(listOf(Card(Rank.SIX, Suit.DIAMONDS), Card(Rank.FIVE, Suit.CLUBS))) // 11

        player.updateWinningMoney(dealer)

        val result = dealer.returnWinningMoneyForDealer(listOf(player))
        assertThat(result).isEqualTo(0)
    }
}
