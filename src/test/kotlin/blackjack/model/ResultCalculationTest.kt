package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultCalculationTest {
    val player1 = Player("Pobi")
    val dealer = Dealer()

    @Test
    fun `dealer should get bet of the player when is only with Blackjack`() {
        player1.placeBet(30000)
        val card1 = Card(Rank.ACE, Suit.HEARTS)
        val card2 = Card(Rank.JACK, Suit.HEARTS)
        val card3 = Card(Rank.TWO, Suit.HEARTS)
        val card4 = Card(Rank.THREE, Suit.HEARTS)
        dealer.drawCard(card1)
        dealer.drawCard(card2)
        player1.drawCard(card3)
        player1.drawCard(card4)

        player1.receiveEarningsOrLoses(ResultCalculation.calculatePlayerResult(player1, dealer))
        val listOfPlayer = Players(listOf(player1))
        dealer.calculateBalance(listOfPlayer.calculateTotalPlayersEarning())
        assertThat(dealer.balance).isEqualTo(30000)
    }
}
