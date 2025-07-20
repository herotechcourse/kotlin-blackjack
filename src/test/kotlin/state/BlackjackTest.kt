package state

import blackjack.model.Card
import blackjack.model.Hand
import blackjack.model.Rank
import blackjack.model.Suit
import blackjack.state.Blackjack
import blackjack.state.Bust
import blackjack.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class BlackjackTest {
    private fun createBlackjackHand(): Hand {
        return Hand(listOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.SPADES, Rank.TEN)))
    }

    @Test
    fun `Blackjack state should be finished and have correct properties`() {
        val blackjackState = Blackjack(createBlackjackHand())
        assertThat(blackjackState.hand.cards).hasSize(2)
        assertThat(blackjackState.hand.calculateSum()).isEqualTo(21)
        assertThat(blackjackState.isRunning()).isFalse()
        assertThat(blackjackState.isFirstTurn()).isFalse()
        assertThat(blackjackState.isBlackJack).isTrue()
    }

    @Test
    fun `should throw IllegalStateException when calling draw in Blackjack state`() {
        val blackjackState = Blackjack(createBlackjackHand())
        assertThrows<IllegalStateException> {
            blackjackState.draw(Card(Suit.DIAMONDS, Rank.FIVE))
        }
    }

    @Test
    fun `should throw IllegalStateException when calling stay in Blackjack state`() {
        val blackjackState = Blackjack(createBlackjackHand())
        assertThrows<IllegalStateException> {
            blackjackState.stay()
        }
    }

    @Test
    fun `should return DRAW_TIE profit when dealer also has Blackjack`() {
        val blackjackState = Blackjack(createBlackjackHand())
        val dealerBlackjackState = Blackjack(Hand(listOf(Card(Suit.CLUBS, Rank.ACE), Card(Suit.DIAMONDS, Rank.TEN))))
        val bet = 100

        val profit = blackjackState.profit(dealerBlackjackState, bet)
        assertThat(profit).isEqualTo(0.0)
    }

    @Test
    fun `should return BLACKJACK_WIN profit when dealer does not have Blackjack`() {
        val blackjackState = Blackjack(createBlackjackHand())
        val dealerStayState = Stay(Hand(listOf(Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.EIGHT))))
        val bet = 100

        val profit = blackjackState.profit(dealerStayState, bet)
        assertThat(profit).isEqualTo(150.0)
    }

    @Test
    fun `should return BLACKJACK_WIN profit when dealer busts`() {
        val blackjackState = Blackjack(createBlackjackHand())
        val dealerBustState =
            Bust(
                Hand(
                    listOf(
                        Card(Suit.CLUBS, Rank.TEN),
                        Card(Suit.DIAMONDS, Rank.TEN),
                        Card(Suit.SPADES, Rank.FIVE),
                    ),
                ),
            )
        val bet = 100

        val profit = blackjackState.profit(dealerBustState, bet)
        assertThat(profit).isEqualTo(150.0)
    }
}
