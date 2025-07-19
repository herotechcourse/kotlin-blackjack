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

class BustTest {
    private fun createBustHand(): Hand {
        return Hand(listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.TEN), Card(Suit.CLUBS, Rank.FIVE)))
    }

    @Test
    fun `Bust state should be finished and have correct properties`() {
        val bustState = Bust(createBustHand())
        assertThat(bustState.hand.cards).hasSize(3)
        assertThat(bustState.hand.calculateSum()).isEqualTo(25)
        assertThat(bustState.isRunning()).isFalse()
        assertThat(bustState.isFirstTurn()).isFalse()
        assertThat(bustState.isBlackJack).isFalse()
    }

    @Test
    fun `should throw IllegalStateException when calling draw in Bust state`() {
        val bustState = Bust(createBustHand())
        assertThrows<IllegalStateException> {
            bustState.draw(Card(Suit.DIAMONDS, Rank.FIVE))
        }
    }

    @Test
    fun `should throw IllegalStateException when calling stay in Bust state`() {
        val bustState = Bust(createBustHand())
        assertThrows<IllegalStateException> {
            bustState.stay()
        }
    }

    @Test
    fun `should always return LOSE_LOSS profit when player busts`() {
        val bustState = Bust(createBustHand())
        val dealerState = Stay(Hand(listOf(Card(Suit.CLUBS, Rank.TEN))))
        val bet = 100

        val profit = bustState.profit(dealerState, bet)
        assertThat(profit).isEqualTo(-100.0)
    }

    @Test
    fun `should return LOSE_LOSS even if dealer also busts`() {
        val bustState = Bust(createBustHand())
        val dealerBustState = Bust(Hand(listOf(Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.TEN), Card(Suit.SPADES, Rank.FIVE))))
        val bet = 100

        val profit = bustState.profit(dealerBustState, bet)
        assertThat(profit).isEqualTo(-100.0)
    }

    @Test
    fun `should return LOSE_LOSS even if dealer has Blackjack`() {
        val bustState = Bust(createBustHand())
        val dealerBlackjackState = Blackjack(Hand(listOf(Card(Suit.CLUBS, Rank.ACE), Card(Suit.DIAMONDS, Rank.KING))))
        val bet = 100

        val profit = bustState.profit(dealerBlackjackState, bet)
        assertThat(profit).isEqualTo(-100.0)
    }
}
