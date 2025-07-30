package blackjack

import blackjack.model.Bet
import blackjack.model.Results
import blackjack.model.cards.HandCards
import blackjack.model.states.BlackJack
import blackjack.model.states.Bust
import blackjack.model.states.Stay
import blackjack.model.table.Dealer
import blackjack.model.table.Player
import blackjack.model.table.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitTest {
    @Test
    fun `profit() returns correct amount when player gets blackjack, dealer doesn't`() {
        val dealerState = Stay(TWENTY_ONE_HAND)
        val dealer = Dealer(state = dealerState)

        val playerState = BlackJack(BLACKJACK_HAND)
        val player = Player("player", playerState, Bet.of(100))

        val results = Results(dealer, Players(listOf(player)))
        val (dealerResult, playersResult) = results.calculate()

        assertThat(dealerResult).isEqualTo(-150.0)
        assertThat(playersResult[player]).isEqualTo(150.0)
    }

    @Test
    fun `profit() returns correct amount when dealer gets blackjack, player doesn't`() {
        val dealerState = BlackJack(BLACKJACK_HAND)
        val dealer = Dealer(state = dealerState)

        val playerState = Stay(TWENTY_ONE_HAND)
        val player = Player("player", playerState, Bet.of(100))

        val results = Results(dealer, Players(listOf(player)))
        val (dealerResult, playersResult) = results.calculate()

        assertThat(dealerResult).isEqualTo(100.0)
        assertThat(playersResult[player]).isEqualTo(-100.0)
    }

    @Test
    fun `profit() returns correct amount when both dealer and player get blackjack`() {
        val dealerState = BlackJack(BLACKJACK_HAND)
        val dealer = Dealer(state = dealerState)

        val playerState = BlackJack(BLACKJACK_HAND)
        val player = Player("player", playerState, Bet.of(100))

        val results = Results(dealer, Players(listOf(player)))
        val (dealerResult, playersResult) = results.calculate()

        assertThat(dealerResult).isEqualTo(0.0)
        assertThat(playersResult[player]).isEqualTo(0.0)
    }

    @Test
    fun `profit() returns correct amount when player busts`() {
        val dealerState = Stay(BIG_STAY_HAND)
        val dealer = Dealer(state = dealerState)

        val playerState = Bust(BUST_HAND)
        val player = Player("player", playerState, Bet.of(100))

        val results = Results(dealer, Players(listOf(player)))
        val (dealerResult, playersResult) = results.calculate()

        assertThat(dealerResult).isEqualTo(100.0)
        assertThat(playersResult[player]).isEqualTo(-100.0)
    }

    @Test
    fun `profit() returns correct amount when Dealer busts, player doesn't `() {
        val dealerState = Bust(BUST_HAND)
        val dealer = Dealer(state = dealerState)

        val playerState = Stay(SMALL_STAY_HAND)
        val player = Player("player", playerState, Bet.of(100))

        val results = Results(dealer, Players(listOf(player)))
        val (dealerResult, playersResult) = results.calculate()

        assertThat(dealerResult).isEqualTo(-100.0)
        assertThat(playersResult[player]).isEqualTo(100.0)
    }

    @Test
    fun `profit() returns correct amount when neither busts, player's hand greater than dealer's`() {
        val dealerState = Stay(SMALL_STAY_HAND)
        val dealer = Dealer(state = dealerState)

        val playerState = Stay(BIG_STAY_HAND)
        val player = Player("player", playerState, Bet.of(100))

        val results = Results(dealer, Players(listOf(player)))
        val (dealerResult, playersResult) = results.calculate()

        assertThat(dealerResult).isEqualTo(-100.0)
        assertThat(playersResult[player]).isEqualTo(100.0)
    }

    @Test
    fun `profit() returns correct amount when neither busts, player's hand less than dealer's`() {
        val dealerState = Stay(BIG_STAY_HAND)
        val dealer = Dealer(state = dealerState)

        val playerState = Stay(SMALL_STAY_HAND)
        val player = Player("player", playerState, Bet.of(100))

        val results = Results(dealer, Players(listOf(player)))
        val (dealerResult, playersResult) = results.calculate()

        assertThat(dealerResult).isEqualTo(100.0)
        assertThat(playersResult[player]).isEqualTo(-100.0)
    }

    @Test
    fun `profit() returns correct amount when neither busts, they have a draw`() {
        val dealerState = Stay(SMALL_STAY_HAND)
        val dealer = Dealer(state = dealerState)

        val playerState = Stay(SMALL_STAY_HAND)
        val player = Player("player", playerState, Bet.of(100))

        val results = Results(dealer, Players(listOf(player)))
        val (dealerResult, playersResult) = results.calculate()

        assertThat(dealerResult).isEqualTo(0.0)
        assertThat(playersResult[player]).isEqualTo(0.0)
    }

    companion object {
        private val SMALL_STAY_HAND = HandCards(listOf(NINE_CLUBS, SEVEN_CLUBS))
        private val BIG_STAY_HAND = HandCards(listOf(NINE_CLUBS, FOUR_CLUBS, FIVE_CLUBS))
        private val BUST_HAND = HandCards(listOf(NINE_CLUBS, FOUR_CLUBS, NINE_CLUBS))
        private val BLACKJACK_HAND = HandCards(listOf(ACE_DIAMONDS, KING_CLUBS))
        private val TWENTY_ONE_HAND = HandCards(listOf(NINE_CLUBS, KING_CLUBS, TWO_HEARTS))
    }
}
