package blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `draws the correct card`() {
        val cards =
            listOf(
                Card(CardSuit.SPADE, 10),
                Card(CardSuit.CLUB, 5),
                Card(CardSuit.HEART, 2),
            )
        val deck = Deck(cards.toMutableList())
        val dealer = Dealer(deck)

        val drawCard = dealer.drawCard()
        assertEquals(CardSuit.SPADE, drawCard.suit)
        assertEquals(10, drawCard.number)
        assertEquals(2, deck.cards.size)
    }

    @Test
    fun `draws a card`() {
        val deck = Deck.generate(false)
        Dealer(deck).drawCard()
        val deckSize = deck.cards.size

        assertEquals(47, deckSize, "Should draw a card out of the deck")
    }

    @Test
    fun `given specific cards, returns the true boolean option`() {
        val cards =
            mutableListOf(
                Card(CardSuit.SPADE, 10),
                Card(CardSuit.CLUB, 3),
                Card(CardSuit.HEART, 2),
            )
        val deck = Deck(cards.toMutableList())
        val dealer = Dealer(deck)

        dealer.addCard(Card(CardSuit.SPADE, 10))
        dealer.addCard(Card(CardSuit.CLUB, 3))
        dealer.addCard(Card(CardSuit.HEART, 2))

        assertEquals(true, dealer.shouldHit())
    }

    @Test
    fun `given specific cards, returns the false boolean option`() {
        val cards =
            listOf(
                Card(CardSuit.SPADE, 10),
                Card(CardSuit.CLUB, 8),
                Card(CardSuit.HEART, 2),
            )
        val deck = Deck(cards.toMutableList())
        val dealer = Dealer(deck)

        dealer.addCard(Card(CardSuit.SPADE, 10))
        dealer.addCard(Card(CardSuit.CLUB, 8))
        dealer.addCard(Card(CardSuit.HEART, 2))

        assertEquals(false, dealer.shouldHit())
    }
}
