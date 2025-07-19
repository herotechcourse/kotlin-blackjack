package state

import blackjack.model.Card
import blackjack.model.Hand
import blackjack.model.Rank
import blackjack.model.Suit
import blackjack.state.Blackjack
import blackjack.state.Bust
import blackjack.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StayTest {
    private fun createStayHand(): Hand {
        return Hand(listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN)))
    }

    @Test
    fun `Stay state should be finished and have correct properties`() {
        val stayState = Stay(createStayHand())
        assertThat(stayState.hand.cards).hasSize(2)
        assertThat(stayState.hand.calculateSum()).isEqualTo(17)
        assertThat(stayState.isRunning()).isFalse()
        assertThat(stayState.isFirstTurn()).isFalse()
        assertThat(stayState.isBlackJack).isFalse()
    }

    @Test
    fun `should throw IllegalStateException when calling draw in Stay state`() {
        val stayState = Stay(createStayHand())
        assertThrows<IllegalStateException> {
            stayState.draw(Card(Suit.DIAMONDS, Rank.FIVE))
        }
    }

    @Test
    fun `should return WIN_GAIN profit when player points are higher than dealer's`() {
        val playerStay = Stay(Hand(listOf(Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.TEN))))
        val dealerStay = Stay(Hand(listOf(Card(Suit.HEARTS, Rank.SEVEN), Card(Suit.SPADES, Rank.TEN))))
        val bet = 100
        assertThat(playerStay.profit(dealerStay, bet)).isEqualTo(100.0)
    }

    @Test
    fun `should return LOSE_LOSS profit when player points are lower than dealer's`() {
        val playerStay = Stay(Hand(listOf(Card(Suit.CLUBS, Rank.FIVE), Card(Suit.DIAMONDS, Rank.FIVE))))
        val dealerStay = Stay(Hand(listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.TEN))))
        val bet = 100
        assertThat(playerStay.profit(dealerStay, bet)).isEqualTo(-100.0)
    }

    @Test
    fun `should return DRAW_TIE profit when player points are equal to dealer's`() {
        val playerStay = Stay(Hand(listOf(Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.SEVEN))))
        val dealerStay = Stay(Hand(listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.SEVEN))))
        val bet = 100
        assertThat(playerStay.profit(dealerStay, bet)).isEqualTo(0.0)
    }

    @Test
    fun `should return WIN_GAIN profit when dealer busts`() {
        val playerStay = Stay(Hand(listOf(Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.FIVE))))
        val dealerBust =
            Bust(Hand(listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.TEN), Card(Suit.CLUBS, Rank.FIVE))))
        val bet = 100
        assertThat(playerStay.profit(dealerBust, bet)).isEqualTo(100.0)
    }

    @Test
    fun `should return BLACKJACK_LOSE profit when dealer has Blackjack`() {
        val playerStay = Stay(Hand(listOf(Card(Suit.CLUBS, Rank.TEN), Card(Suit.DIAMONDS, Rank.TEN))))
        val dealerBlackjack = Blackjack(Hand(listOf(Card(Suit.HEARTS, Rank.ACE), Card(Suit.SPADES, Rank.KING))))
        val bet = 100
        assertThat(playerStay.profit(dealerBlackjack, bet)).isEqualTo(-150.0)
    }
}
