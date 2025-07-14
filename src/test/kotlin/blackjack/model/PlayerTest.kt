package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlayerTest {
    @Test
    fun `player should have given name`() {
        val player = Player("Alex", 20000)
        assertEquals("Alex", player.name)
    }

    @Test
    fun `player should have correct amount of bet`() {
        val player = Player("Alex", 20000)
        assertEquals(20000, player.bet)
    }

    @Test
    fun `draw method should add cards to cardsInHand`() {
        val player = Player("Alex", 20000)
        val card = Card(Rank.KING, Suit.HEARTS)
        assertThat(player.cardsInHand).hasSize(0)
        player.drawCard(listOf(card))
        assertThat(player.cardsInHand).hasSize(1)
    }

    @Test
    fun `player total calculates aces correctly`() {
        val player = Player("Farhana", 10000)
        player.drawCard(listOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.SIX, Suit.CLUBS)))
        assertEquals(17, player.total()) // ace=11 + six=6, total =17

        player.drawCard(listOf(Card(Rank.ACE, Suit.DIAMONDS)))
        assertEquals(18, player.total()) // ace1=11 + six=6 + ace2=1, total =18
    }

    @Test
    fun `player busts when total exceeds 21`() {
        val player = Player("Alex", 20000)
        player.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.KING, Suit.SPADES), Card(Rank.TWO, Suit.CLUBS)))
        assertTrue(player.isBusted()) // total = 22
    }

    @Test
    fun `player is still in game when total is 21 or less`() {
        val player = Player("Farhana", 10000)
        player.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.ACE, Suit.SPADES)))
        assertTrue(player.isStillInGame()) // total = 21
    }

    @Test
    fun `should calculate correct winning money for blackjack`() {
        val dealer = Dealer()
        val player = Player("pobi", 10000)
        player.drawCard(listOf(Card(Rank.ACE, Suit.SPADES), Card(Rank.KING, Suit.HEARTS)))
        dealer.drawCard(listOf(Card(Rank.FIVE, Suit.CLUBS), Card(Rank.FOUR, Suit.DIAMONDS)))
        player.updateWinningMoney(dealer)
        assertThat(player.returnWinningMoneyForPlayer()).isEqualTo(15000)
    }

    @Test
    fun `should calculate correct winning money without blackjack`() {
        val dealer = Dealer()
        val player = Player("pobi", 10000)

        // initial draw
        player.drawCard(listOf(Card(Rank.SIX, Suit.SPADES), Card(Rank.KING, Suit.HEARTS))) // total = 16
        dealer.drawCard(listOf(Card(Rank.FIVE, Suit.CLUBS), Card(Rank.FOUR, Suit.DIAMONDS))) // total = 9

        // player draws again
        player.drawCard(cards = listOf(Card(Rank.FIVE, Suit.DIAMONDS))) // total = 21

        // dealer draws again
        dealer.drawCard(listOf(Card(Rank.FIVE, Suit.SPADES))) // total = 14

        player.updateWinningMoney(dealer)
        assertThat(player.returnWinningMoneyForPlayer()).isEqualTo(10000)
    }

    @Test
    fun `should lose bet when busted`() {
        val dealer = Dealer()
        val player = Player("test", 10000)

        player.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.SIX, Suit.CLUBS)))
        dealer.drawCard(listOf(Card(Rank.NINE, Suit.HEARTS), Card(Rank.NINE, Suit.DIAMONDS)))

        player.updateWinningMoney(dealer)

        assertThat(player.returnWinningMoneyForPlayer()).isEqualTo(-10000)
    }
}
