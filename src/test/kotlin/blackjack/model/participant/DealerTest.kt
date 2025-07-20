package blackjack.model.participant

import blackjack.TestFixture
import blackjack.TestFixture.TwoCards.TWO_CARDS_BLACKJACK
import blackjack.TestFixture.TwoCards.TWO_CARDS_SUM_16
import blackjack.TestFixture.TwoCards.TWO_CARDS_SUM_17
import blackjack.TestFixture.TwoCards.TWO_CARDS_SUM_18
import blackjack.model.state.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `dealer hits when score is 16 or less`() {
        val dealer = Dealer()
        dealer.receive(TWO_CARDS_SUM_16)

        assertThat(dealer.state).isEqualTo(State.HIT)
    }

    @Test
    fun `dealer stays when score is exactly 17`() {
        val dealer = Dealer()
        dealer.receive(TWO_CARDS_SUM_17)

        assertThat(dealer.state).isEqualTo(State.STAY)
    }

    @Test
    fun `dealer stays when score is above 17 but less than blackjack`() {
        val dealer = Dealer()
        dealer.receive(TWO_CARDS_SUM_18)

        assertThat(dealer.state).isEqualTo(State.STAY)
    }

    @Test
    fun `dealer busts when score exceeds 21`() {
        val dealer = Dealer()
        dealer.receive(TWO_CARDS_SUM_16)
        dealer.receive(listOf(TestFixture.Card.DIAMOND_JACK))

        assertThat(dealer.state).isEqualTo(State.BUST)
        assertThat(dealer.isBust()).isTrue()
    }

    @Test
    fun `dealer blackjack on first round`() {
        val dealer = Dealer()
        dealer.receive(TWO_CARDS_BLACKJACK)

        assertThat(dealer.state).isEqualTo(State.BLACKJACK)
        assertThat(dealer.isBlackjack()).isTrue()
    }
}
