package blackjack

import blackjack.model.Card
import blackjack.model.Deck
import blackjack.model.Gambler
import blackjack.model.GamblerInfo
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `should be equal result`() {
        val card = Card(Rank.TEN, Suit.SPADE)
        val player = Gambler(GamblerInfo("Player"))
        player.addCard(listOf(card))
        assertEquals(10, player.score)
    }

    @Test
    fun `Ace card is considered one if score crosses 21`() {
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val player = Gambler(GamblerInfo("Player"))
        player.addCard(cards)
        assertEquals(12, player.score)
    }

    @Test
    fun `Ace card is considered 11 if the new score is less than 21`() {
        val cards = listOf(Card(Rank.FOUR, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val player = Gambler(GamblerInfo("Player"))
        player.addCard(cards)
        assertEquals(15, player.score)
    }

    @Test
    fun `should return 13 - Ace's rank is converted to 1 if score greater than 21`() {
        val cards =
            listOf(
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.KING, Suit.HEART),
            )
        val player = Gambler(GamblerInfo("Player"))
        player.addCard(cards)
        assertEquals(13, player.score)
    }

    @Test
    fun `should return 12 - both Ace's rank is converted to 1 if score greater than 21`() {
        val cards =
            listOf(
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.KING, Suit.HEART),
            )
        val player = Gambler(GamblerInfo("Player"))
        player.addCard(cards)
        assertEquals(12, player.score)
    }

    @Test
    fun `should return 22 - each Ace rank is converted to 1 if score greater than 21`() {
        val cards =
            listOf(
                Card(Rank.NINE, Suit.SPADE),
                Card(Rank.NINE, Suit.HEART),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.ACE, Suit.CLUB),
                Card(Rank.ACE, Suit.DIAMOND),
            )
        val player = Gambler(GamblerInfo("Player"))
        player.addCard(cards)
        assertEquals(22, player.score)
    }

    @Test
    fun `should get cards`() {
        val deck = Deck()
        val player = Gambler(GamblerInfo("Jin"))
        player.addCard(deck.drawCards(4))
        assertThat(player.cards.size).isEqualTo(4)
    }
}
