package blackjack.utils

import blackjack.model.Card
import blackjack.model.Rank
import blackjack.model.Suit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CardGeneratorTest {
    @Test
    fun `generateCards() - create a deck of cards`() {
        val cards = CardGenerator.generateCards()
        assertTrue(cards.contains(Card(Rank.ACE, Suit.HEARTS)))
    }

    @Test
    fun `generateCards() - cards do not have duplicates`() {
        val cards = CardGenerator.generateCards()
        assertEquals(cards.toSet().size, cards.size)
    }

    @Test
    fun `generateCards() - contains all ranks and suits`() {
        val cards = CardGenerator.generateCards()

        Rank.entries.filter { it != Rank.NONE }.forEach { rank ->
            Suit.entries.filter { it != Suit.NONE }.forEach { suit ->
                assertTrue(cards.contains(Card(rank, suit)), "Missing card: $rank of $suit")
            }
        }
    }
}
