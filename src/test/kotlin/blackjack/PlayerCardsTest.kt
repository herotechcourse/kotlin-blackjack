package blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayerCardsTest {
    @Test
    fun `should be equal result`() {
        val card = Card(Rank.TEN, Suit.SPADE)
        val playerCards = PlayerCards()
        playerCards.addCard(card)
        assertEquals(10, playerCards.score)
    }

    @Test
    fun `Ace card is considered one if score crosses 21`() {
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val playerCards = PlayerCards()
        cards.forEach { it -> playerCards.addCard(it) }
        assertEquals(12, playerCards.score)
    }

    @Test
    fun `Ace card is considered 11 if the new score is less than 21`() {
        val cards = listOf(Card(Rank.FOUR, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val playerCards = PlayerCards()
        cards.forEach { it -> playerCards.addCard(it) }
        assertEquals(15, playerCards.score)
    }
}
