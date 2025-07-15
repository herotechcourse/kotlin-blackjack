package blackjack.model

import blackjack.TestCards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `if BlackJack should not play`() {
        val dealer = Dealer()
        val cardDeck = CardDeck()
        dealer.state = dealer.state.draw(TestCards.QUEEN).draw(TestCards.Ace)
        dealer.playTurn(cardDeck)

        assertThat(dealer.state.hand.size).isEqualTo(2)
    }

    @Test
    fun `if Running(Hit) and less then 17 should draw`() {
        val dealer = Dealer()
        val cardDeck = CardDeck()
        dealer.state = dealer.state.draw(TestCards.QUEEN).draw(TestCards.FOUR)
        dealer.playTurn(cardDeck)

        assertThat(dealer.state.hand.size).isEqualTo(3)
    }
}
