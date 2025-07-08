package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `CardDeck has 52 cards`() {
        val cardDeck = CardDeck()
        assertThat(cardDeck.numberOfCards()).isEqualTo(52)
    }
}
