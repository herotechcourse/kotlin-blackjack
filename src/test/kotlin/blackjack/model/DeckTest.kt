package blackjack.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `generate cards with suit`() {
        val deck = Deck.generate(true)
        val cardsAmount = deck.cards.size
        Assertions.assertEquals(52, cardsAmount, "Cards amount aren't 52")
    }
}
