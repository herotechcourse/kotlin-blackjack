package blackjack

import blackjack.model.Bet
import blackjack.model.Results
import blackjack.model.states.Bust
import blackjack.model.states.Stay
import blackjack.model.table.Dealer
import blackjack.model.table.Player
import blackjack.model.table.Players
import org.assertj.core.api.Assertions.assertThat
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test

class ResultsTest {
    @Test
    fun `calculate() method should return correct amount for dealer and players`() {
        val dealerState = mock<Bust>()
        val dealer = Dealer(state = dealerState)

        val playerState = mock<Stay>()
        val player =
            mock<Player> {
                on { bet } doReturn Bet.of(100)
                on { state } doReturn playerState
            }

        whenever(playerState.profit(Bet.of(100), dealerState)).thenReturn(100.0)

        val results = Results(dealer, Players(listOf(player)))
        val (dealerResult, playersResult) = results.calculate()

        assertThat(dealerResult).isEqualTo(-100.0)
        assertThat(playersResult[player]).isEqualTo(100.0)
    }
}
