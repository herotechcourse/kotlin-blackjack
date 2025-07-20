package blackjack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `draws the correct card` () {
        val cards = listOf(
            Card( CardSuit.SPADE, 10),
            Card(CardSuit.CLUB, 5),
            Card(CardSuit.HEART, 2)
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
}