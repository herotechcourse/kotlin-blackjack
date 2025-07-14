package service

import model.Bet
import model.Card
import model.Dealer
import model.Player
import model.Players
import model.Rank
import model.Suite
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EarningCalculatorTest {
    @Test
    fun `Player loses`() {
        val player1 = Player("A")
        player1.bet = Bet(1000.0)
        val dealer = Dealer()
        val players = Players(listOf(player1))
        player1.drawCard(Card(Rank.THREE, Suite.DIAMONDS))
        player1.drawCard(Card(Rank.ACE, Suite.DIAMONDS))

        dealer.drawCard(Card(Rank.TEN, Suite.DIAMONDS))
        dealer.drawCard(Card(Rank.NINE, Suite.HEARTS))
        val dealerHasBlackJack = dealer.isBlackJack()

        assertThat(ResultCalculator.calculateEarning(players, dealer.getScore(), dealerHasBlackJack)).isEqualTo(
            listOf(
                PlayerEarningResult(0, -1000.0, 1000.0),
            ),
        )
    }

    @Test
    fun `Draw with blackjack`() {
        val player1 = Player("A")
        player1.bet = Bet(1000.0)

        val dealer = Dealer()
        val players = Players(listOf(player1))
        player1.drawCard(Card(Rank.JACK, Suite.DIAMONDS))
        player1.drawCard(Card(Rank.ACE, Suite.DIAMONDS))

        dealer.drawCard(Card(Rank.TEN, Suite.HEARTS))
        dealer.drawCard(Card(Rank.ACE, Suite.HEARTS))
        val dealerHasBlackJack = dealer.isBlackJack()

        assertThat(ResultCalculator.calculateEarning(players, dealer.getScore(), dealerHasBlackJack)).isEqualTo(
            listOf(
                PlayerEarningResult(0, 0.0, 0.0),
            ),
        )
    }

    @Test
    fun `Draw without blackjack`() {
        val player1 = Player("A")
        player1.bet = Bet(1000.0)

        val dealer = Dealer()
        val players = Players(listOf(player1))
        player1.drawCard(Card(Rank.TEN, Suite.DIAMONDS))
        player1.drawCard(Card(Rank.TEN, Suite.DIAMONDS))

        dealer.drawCard(Card(Rank.TEN, Suite.HEARTS))
        dealer.drawCard(Card(Rank.TEN, Suite.HEARTS))
        val dealerHasBlackJack = dealer.isBlackJack()

        assertThat(ResultCalculator.calculateEarning(players, dealer.getScore(), dealerHasBlackJack)).isEqualTo(
            listOf(
                PlayerEarningResult(0, 0.0, 0.0),
            ),
        )
    }

    @Test
    fun `Player wins with blackjack`() {
        val player1 = Player("A")
        player1.bet = Bet(1000.0)

        val player2 = Player("B")
        player2.bet = Bet(4000.0)

        val dealer = Dealer()
        val players = Players(listOf(player1, player2))
        player1.drawCard(Card(Rank.TEN, Suite.DIAMONDS))
        player1.drawCard(Card(Rank.ACE, Suite.DIAMONDS))

        player2.drawCard(Card(Rank.JACK, Suite.DIAMONDS))
        player2.drawCard(Card(Rank.SEVEN, Suite.DIAMONDS))
        player2.drawCard(Card(Rank.TWO, Suite.DIAMONDS))

        dealer.drawCard(Card(Rank.TEN, Suite.DIAMONDS))
        dealer.drawCard(Card(Rank.NINE, Suite.HEARTS))
        val dealerHasBlackJack = dealer.isBlackJack()

        assertThat(ResultCalculator.calculateEarning(players, dealer.getScore(), dealerHasBlackJack)).isEqualTo(
            listOf(
                PlayerEarningResult(0, 1500.0, -1500.0),
                PlayerEarningResult(1, 0.0, 0.0),
            ),
        )
    }

    @Test
    fun `Player wins without blackjack`() {
        val player1 = Player("A")
        player1.bet = Bet(1000.0)

        val player2 = Player("B")
        player2.bet = Bet(4000.0)

        val dealer = Dealer()
        val players = Players(listOf(player1, player2))
        player1.drawCard(Card(Rank.THREE, Suite.DIAMONDS))
        player1.drawCard(Card(Rank.ACE, Suite.DIAMONDS))

        player2.drawCard(Card(Rank.JACK, Suite.DIAMONDS))
        player2.drawCard(Card(Rank.QUEEN, Suite.DIAMONDS))

        dealer.drawCard(Card(Rank.TEN, Suite.DIAMONDS))
        dealer.drawCard(Card(Rank.NINE, Suite.HEARTS))
        val dealerHasBlackJack = dealer.isBlackJack()

        assertThat(ResultCalculator.calculateEarning(players, dealer.getScore(), dealerHasBlackJack)).isEqualTo(
            listOf(
                PlayerEarningResult(0, -1000.0, 1000.0),
                PlayerEarningResult(1, 4000.0, -4000.0),
            ),
        )
    }

    @Test
    fun `all Players lose`() {
        val player1 = Player("A")
        player1.bet = Bet(1000.0)

        val player2 = Player("B")
        player2.bet = Bet(1000.0)

        val dealer = Dealer()
        val players = Players(listOf(player1, player2))
        player1.drawCard(Card(Rank.THREE, Suite.DIAMONDS))
        player1.drawCard(Card(Rank.ACE, Suite.DIAMONDS))

        player2.drawCard(Card(Rank.JACK, Suite.DIAMONDS))
        player2.drawCard(Card(Rank.THREE, Suite.DIAMONDS))

        dealer.drawCard(Card(Rank.TEN, Suite.DIAMONDS))
        dealer.drawCard(Card(Rank.NINE, Suite.HEARTS))
        val dealerHasBlackJack = dealer.isBlackJack()

        assertThat(ResultCalculator.calculateEarning(players, dealer.getScore(), dealerHasBlackJack)).isEqualTo(
            listOf(
                PlayerEarningResult(0, -1000.0, 1000.0),
                PlayerEarningResult(1, -1000.0, 1000.0),
            ),
        )
        ResultCalculator.applyEarningResult(
            players,
            dealer,
            (
                listOf(
                    PlayerEarningResult(0, -1000.0, 1000.0),
                    PlayerEarningResult(1, -1000.0, 1000.0),
                )
            ),
        )
        assertThat(dealer.earnings).isEqualTo(2000.0)
    }

    @Test
    fun `Player loses when dealer has blackjack and player has 21`() {
        val player1 = Player("A")
        player1.bet = Bet(1000.0)

        val player2 = Player("B")
        player2.bet = Bet(4000.0)

        val dealer = Dealer()
        val players = Players(listOf(player1, player2))
        player1.drawCard(Card(Rank.THREE, Suite.DIAMONDS))
        player1.drawCard(Card(Rank.SEVEN, Suite.DIAMONDS))
        player1.drawCard(Card(Rank.ACE, Suite.DIAMONDS))

        player2.drawCard(Card(Rank.JACK, Suite.DIAMONDS))
        player2.drawCard(Card(Rank.QUEEN, Suite.DIAMONDS))

        dealer.drawCard(Card(Rank.TEN, Suite.DIAMONDS))
        dealer.drawCard(Card(Rank.ACE, Suite.HEARTS))
        val dealerHasBlackJack = dealer.isBlackJack()

        assertThat(ResultCalculator.calculateEarning(players, dealer.getScore(), dealerHasBlackJack)).isEqualTo(
            listOf(
                PlayerEarningResult(0, -1000.0, 1000.0),
                PlayerEarningResult(1, -4000.0, 4000.0),
            ),
        )
    }
}
