package blackjack.model

import blackjack.state.Blackjack
import blackjack.state.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `should initialize a Dealer with a default name Dealer`() {
        assertThat(Dealer()).isInstanceOf(Dealer::class.java)
    }

    @Test
    fun `should return false when dealer has more than 16`() {
        val dealer = Dealer()
        dealer.handState.draw(Card(Suit.HEARTS, Rank.NINE))
        dealer.handState.draw(Card(Suit.SPADES, Rank.NINE))

        val dealerSum = dealer.handState.hand.calculateSum()
        val shouldDealerContinue = dealer.handState.isRunning() && dealerSum >= Dealer.DEALER_STAND
        assertThat(shouldDealerContinue).isFalse()
    }

    @Test
    fun `should return true when dealer has 16 or less`() {
        val dealer = Dealer()
        dealer.handState.draw(Card(Suit.HEARTS, Rank.NINE))
        dealer.handState.draw(Card(Suit.SPADES, Rank.TWO))

        val dealerSum = dealer.handState.hand.calculateSum()
        val shouldDealerContinue = dealer.handState.isRunning() && dealerSum <= Dealer.DEALER_STAND
        assertThat(shouldDealerContinue).isTrue()
    }

    @Test
    fun `dealInitialCards should give the dealer two cards`() {
        val dealer = Dealer()
        val deck = Deck(mutableListOf(Card(Suit.HEARTS, Rank.SEVEN), Card(Suit.SPADES, Rank.FIVE)))

        dealer.dealInitialCards(deck)

        assertThat(dealer.handState.hand.size).isEqualTo(2)
        assertThat(dealer.points).isEqualTo(12)
        assertThat(dealer.handState).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `playTurn should not enter hit state if has blackjack initially `() {
        val dealer = Dealer()
        val deck1 = Deck(mutableListOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.ACE)))
        dealer.dealInitialCards(deck1)

        assertThat(dealer.points).isEqualTo(21)
        assertThat(dealer.handState).isInstanceOf(Blackjack::class.java)

        val deck2 = Deck(mutableListOf(Card(Suit.HEARTS, Rank.TEN)))

        val deckTwoSize = deck2.size

        dealer.playTurn(deck2)

        assertThat(dealer.points).isEqualTo(21)
        assertThat(dealer.handState).isInstanceOf(Blackjack::class.java)
        assertThat(dealer.handState.hand.cards).hasSize(2)
        assertThat(deck2.size).isEqualTo(deckTwoSize)
    }
}
