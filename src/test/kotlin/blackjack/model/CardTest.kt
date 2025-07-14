package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `card display returns correct symbol`() {
        val card = Card(Suit.SPADE, Rank.KING)
        assertEquals("K♠", card.display())
    }

    @Test
    fun `card score returns correct value`() {
        val card = Card(Suit.HEART, Rank.FIVE)
        assertEquals(5, card.getScore())
    }

    @Test
    fun `ace is correctly recognized`() {
        val aceCard = Card(Suit.DIAMOND, Rank.ACE)
        assertTrue(aceCard.isAce())

        val nonAceCard = Card(Suit.CLUB, Rank.TEN)
        assertFalse(nonAceCard.isAce())
    }
}
