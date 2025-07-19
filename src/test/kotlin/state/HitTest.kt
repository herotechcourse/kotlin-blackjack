package state

import blackjack.model.Card
import blackjack.model.Hand
import blackjack.model.Rank
import blackjack.model.Suit
import blackjack.state.Bust
import blackjack.state.Hit
import blackjack.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HitTest {
    private fun createHitHand(): Hand {
        return Hand(listOf(Card(Suit.SPADES, Rank.SEVEN), Card(Suit.SPADES, Rank.SEVEN)))
    }

    @Test
    fun `initial Hit State should have correct Hand and be running`() {
        val hitState = Hit(createHitHand())
        assertThat(hitState.hand.cards).hasSize(2)
        assertThat(hitState.hand.calculateSum()).isEqualTo(14)
        assertThat(hitState.isRunning()).isTrue()
        assertThat(hitState.isFirstTurn()).isFalse()
        assertThat(hitState.isBlackJack).isFalse()
    }

    @Test
    fun `should add a card and remain in Hit state if the total is below 21`() {
        val hitState = Hit(createHitHand())
        val card = Card(Suit.SPADES, Rank.THREE)

        val nextState = hitState.draw(card)

        assertThat(nextState).isInstanceOf(Hit::class.java)
        assertThat(nextState.hand.cards).hasSize(3)
        assertThat(nextState.hand.calculateSum()).isEqualTo(17)
        assertThat(nextState.isRunning()).isTrue()
    }

    @Test
    fun `should add a card and move to Bust state if the total is above 21`() {
        val hitState = Hit(createHitHand())
        val card = Card(Suit.SPADES, Rank.TEN)

        val nextState = hitState.draw(card)

        assertThat(nextState).isInstanceOf(Bust::class.java)
        assertThat(nextState.hand.cards).hasSize(3)
        assertThat(nextState.hand.calculateSum()).isEqualTo(24)
        assertThat(nextState.isRunning()).isFalse()
    }

    @Test
    fun `should add a card and remain in Hit state if the total is exactly 21 but not Blackjack`() {
        val hitState = Hit(createHitHand())
        val card = Card(Suit.SPADES, Rank.SEVEN)

        val nextState = hitState.draw(card)

        assertThat(nextState).isInstanceOf(Hit::class.java)
        assertThat(nextState.hand.cards).hasSize(3)
        assertThat(nextState.hand.calculateSum()).isEqualTo(21)
        assertThat(nextState.isRunning()).isTrue()
        assertThat(nextState.isBlackJack).isFalse()
    }

    @Test
    fun `should throw IllegalStateException if profit is called in Hit State`() {
        val hitState = Hit(createHitHand())
        val dealerState = Stay(Hand(listOf(Card(Suit.DIAMONDS, Rank.TEN))))
        assertThrows<IllegalStateException> {
            hitState.profit(dealerState, 100)
        }
    }
}
