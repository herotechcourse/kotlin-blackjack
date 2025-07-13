package blackjack

import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.Rank
import blackjack.model.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {
    @Test
    fun `test number of cards in deck`()  {
        assertEquals(CardDeck().deck.size, 208)
    }

    @Test
    fun `test size of deck`()  {
        val deck1 = CardDeck()
        assertEquals(deck1.size, 208)
    }

    @Test
    fun `test hit function`()  {
        val deck1 = CardDeck()
        deck1.hit()
        val deck2 = CardDeck()
        assertEquals(deck1.size, deck2.size - 1)
    }

    @Test
    fun `test draw more than deck`()  {
        val deck = CardDeck()
        var card = Card(Rank.ACE, Suit.HEARTS)
        repeat(208) {
            card = deck.hit()
        }
        assertThrows<Exception> { card = deck.hit() }
    }
}
