package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    @Test
    fun `CardDeck has 52 cards`() {
        val cardDeck = CardDeck()
        assertThat(cardDeck.numberOfCards()).isEqualTo(52)
    }

    @Test
    fun `hit a card to a player`() {
        val cardDeck = CardDeck()
        val player1 = Player("Mina")
        cardDeck.hit(player1) // should move card to player
        assertThat(player1.numberInHand()).isEqualTo(1)
    }
}
