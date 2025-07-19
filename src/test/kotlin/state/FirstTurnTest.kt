package state

import blackjack.model.Card
import blackjack.model.Hand
import blackjack.model.Rank
import blackjack.model.Suit
import blackjack.state.Blackjack
import blackjack.state.FirstTurn
import blackjack.state.Hit
import blackjack.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FirstTurnTest {
    @Test
    fun `initial FirstTurn state should have empty hand and be running`() {
        val firstTurn = FirstTurn()
        assertThat(firstTurn.hand.cards).isEmpty()
        assertThat(firstTurn.isRunning()).isTrue()
        assertThat(firstTurn.isFirstTurn()).isTrue()
        assertThat(firstTurn.isBlackJack).isFalse()
    }

    @Test
    fun `should add a second card and move to Hit state if the total is not 21`() {
        val firstTurn = FirstTurn(Hand(listOf(Card(Suit.SPADES, Rank.SEVEN))))
        val card = Card(Suit.CLUBS, Rank.FIVE)

        val nextState = firstTurn.draw(card)

        assertThat(nextState).isInstanceOf(Hit::class.java)
        assertThat(nextState.hand.cards).containsExactly(Card(Suit.SPADES, Rank.SEVEN), card)
        assertThat(nextState.hand.size).isEqualTo(2)
        assertThat(nextState.hand.calculateSum()).isEqualTo(12)
        assertThat(nextState.isRunning()).isTrue()
        assertThat(nextState.isFirstTurn()).isFalse()
    }

    @Test
    fun `should add second card and transition to Blackjack state if total is 21`() {
        val firstTurn = FirstTurn(Hand(listOf(Card(Suit.SPADES, Rank.TEN))))
        val card = Card(Suit.CLUBS, Rank.ACE)

        val nextState = firstTurn.draw(card)

        assertThat(nextState).isInstanceOf(Blackjack::class.java)
        assertThat(nextState.hand.cards).containsExactly(Card(Suit.SPADES, Rank.TEN), card)
        assertThat(nextState.hand.size).isEqualTo(2)
        assertThat(nextState.hand.calculateSum()).isEqualTo(21)
        assertThat(nextState.isRunning()).isFalse()
        assertThat(nextState.isFirstTurn()).isFalse()
        assertThat(nextState.isBlackJack).isTrue()
    }

    @Test
    fun `should throw IllegalStateException when calling stay in FirstTurn state`() {
        val firstTurn = FirstTurn()
        assertThrows<IllegalStateException> {
            firstTurn.stay()
        }
    }

    @Test
    fun `should throw IllegalStateException when calling profit in FirstTurn state`() {
        val firstTurn = FirstTurn()
        val dealerState = Stay(Hand(listOf(Card(Suit.HEARTS, Rank.TEN))))
        assertThrows<IllegalStateException> {
            firstTurn.profit(dealerState, 100)
        }
    }
}
