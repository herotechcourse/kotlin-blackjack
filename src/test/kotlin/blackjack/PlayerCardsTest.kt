package blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayerCardsTest {
    @Test
    fun `should be equal result`() {
        val card = Card(Rank.TEN, Suit.SPADE)
        val playerCards = PlayerCards()
        playerCards.addCard(listOf(card))
        assertEquals(10, playerCards.score)
    }

    @Test
    fun `Ace card is considered one if score crosses 21`() {
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val playerCards = PlayerCards()
        playerCards.addCard(cards)
        assertEquals(12, playerCards.score)
    }

    @Test
    fun `Ace card is considered 11 if the new score is less than 21`() {
        val cards = listOf(Card(Rank.FOUR, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val playerCards = PlayerCards()
        playerCards.addCard(cards)
        assertEquals(15, playerCards.score)
    }

    @Test
    fun `should return 13 - Ace's rank is converted to 1 if score greater than 21`() {
        val cards =
            listOf(
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.KING, Suit.HEART),
            )
        val playerCards = PlayerCards()
        playerCards.addCard(cards)
        assertEquals(13, playerCards.score)
    }

    @Test
    fun `should return 12 - both Ace's rank is converted to 1 if score greater than 21`() {
        val cards =
            listOf(
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.KING, Suit.HEART),
            )
        val playerCards = PlayerCards()
        playerCards.addCard(cards)
        assertEquals(12, playerCards.score)
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
                Card(Rank.ACE, Suit.DIAMOND)
            )
        val playerCards = PlayerCards()
        playerCards.addCard(cards)
        assertEquals(22, playerCards.score)
    }
}
