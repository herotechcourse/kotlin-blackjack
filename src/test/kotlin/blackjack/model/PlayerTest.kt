package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlayerTest {
    @Test
    fun `player should have given name`() {
        val player = Player("Alex")
        assertEquals("Alex", player.name)
    }

    @Test
    fun `draw method should add cards to cardsInHand`() {
        val player = Player("Alex")
        val card = Card(Rank.KING, Suit.HEARTS)
        assertThat(player.cardsInHand).hasSize(0)
        player.drawCard(listOf(card))
        assertThat(player.cardsInHand).hasSize(1)
    }

    @Test
    fun `player total calculates aces correctly`() {
        val player = Player("Farhana")
        player.drawCard(listOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.SIX, Suit.CLUBS)))
        assertEquals(17, player.total()) // ace=11 + six=6, total =17

        player.drawCard(listOf(Card(Rank.ACE, Suit.DIAMONDS)))
        assertEquals(18, player.total()) // ace1=11 + six=6 + ace2=1, total =18
    }

    @Test
    fun `player busts when total exceeds 21`() {
        val player = Player("Alex")
        player.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.KING, Suit.SPADES), Card(Rank.TWO, Suit.CLUBS)))
        assertTrue(player.isBusted()) // total = 22
    }

    @Test
    fun `player is still in game when total is 21 or less`() {
        val player = Player("Farhana")
        player.drawCard(listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.ACE, Suit.SPADES)))
        assertTrue(player.isStillInGame()) // total = 21
    }
}
