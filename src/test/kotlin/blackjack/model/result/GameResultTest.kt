package blackjack.model.result

import blackjack.DummyPlayerFactory
import blackjack.TestFixture.BustedWithLastCard.THREE_CARDS_SUM_24
import blackjack.TestFixture.DoesNotHasAce.THREE_CARDS_SUM_21
import blackjack.TestFixture.TwoCards.TWO_CARDS_BLACKJACK
import blackjack.TestFixture.TwoCards.TWO_CARDS_SUM_17
import blackjack.TestFixture.TwoCards.TWO_CARDS_SUM_20
import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `player gets BLACKJACK outcome when achieving blackjack and dealer does not`() {
        val participants = Participants(DummyPlayerFactory(1).players)
        val dealer = participants.dealer
        val player = participants.players[0]

        player.receive(TWO_CARDS_BLACKJACK)
        dealer.receive(TWO_CARDS_SUM_20)

        val gameResult = GameResult(participants)
        val playerResult = gameResult.playerResults[0]

        assertThat(player.isBlackjack()).isTrue()
        assertThat(dealer.isBlackjack()).isFalse()
        assertThat(playerResult.outcome).isEqualTo(Outcome.BLACKJACK)
        assertThat(playerResult.finalAmount).isEqualTo(25000)
    }

    @Test
    fun `player gets WIN outcome when scoring 21 with more than 2 cards`() {
        val participants = Participants(DummyPlayerFactory(1).players)
        val dealer = participants.dealer
        val player = participants.players[0]

        player.receive(THREE_CARDS_SUM_21)
        dealer.receive(TWO_CARDS_SUM_20)

        val gameResult = GameResult(participants)
        val playerResult = gameResult.playerResults[0]

        assertThat(player.score).isEqualTo(21)
        assertThat(player.isBlackjack()).isFalse()
        assertThat(dealer.score).isEqualTo(20)
        assertThat(playerResult.outcome).isEqualTo(Outcome.WIN)
        assertThat(playerResult.finalAmount).isEqualTo(20000)
    }

    @Test
    fun `both player and dealer blackjack results in DRAW`() {
        val participants = Participants(DummyPlayerFactory(1).players)
        val dealer = participants.dealer
        val player = participants.players[0]

        player.receive(TWO_CARDS_BLACKJACK)
        dealer.receive(TWO_CARDS_BLACKJACK)

        val gameResult = GameResult(participants)
        val playerResult = gameResult.playerResults[0]

        assertThat(player.isBlackjack()).isTrue()
        assertThat(dealer.isBlackjack()).isTrue()
        assertThat(playerResult.outcome).isEqualTo(Outcome.DRAW)
        assertThat(playerResult.finalAmount).isEqualTo(10000)
    }

    @Test
    fun `player with 20 loses to dealer blackjack`() {
        val participants = Participants(DummyPlayerFactory(1).players)
        val dealer = participants.dealer
        val player = participants.players[0]

        player.receive(TWO_CARDS_SUM_20)
        dealer.receive(TWO_CARDS_BLACKJACK)

        val gameResult = GameResult(participants)
        val playerResult = gameResult.playerResults[0]

        assertThat(player.score).isEqualTo(20)
        assertThat(player.isBlackjack()).isFalse()
        assertThat(dealer.isBlackjack()).isTrue()
        assertThat(playerResult.outcome).isEqualTo(Outcome.LOSE)
        assertThat(playerResult.finalAmount).isEqualTo(-10000)
    }

    @Test
    fun `player blackjack wins against dealer bust`() {
        val participants = Participants(DummyPlayerFactory(1).players)
        val dealer = participants.dealer
        val player = participants.players[0]

        player.receive(TWO_CARDS_BLACKJACK)
        dealer.receive(THREE_CARDS_SUM_24)

        val gameResult = GameResult(participants)
        val playerResult = gameResult.playerResults[0]

        assertThat(player.isBlackjack()).isTrue()
        assertThat(dealer.isBust()).isTrue()
        assertThat(playerResult.outcome).isEqualTo(Outcome.BLACKJACK)
        assertThat(playerResult.finalAmount).isEqualTo(25000)
    }

    @Test
    fun `multiple players with different outcomes`() {
        val player1 = Player("blackjack", 10000)
        val player2 = Player("win21", 10000)
        val player3 = Player("lose", 10000)
        val players = listOf(player1, player2, player3)
        val participants = Participants(players)
        val dealer = participants.dealer

        player1.receive(TWO_CARDS_BLACKJACK)
        player2.receive(THREE_CARDS_SUM_21)
        player3.receive(TWO_CARDS_SUM_17)

        dealer.receive(TWO_CARDS_SUM_20)

        val gameResult = GameResult(participants)

        val player1Result = gameResult.playerResults.find { it.player.name == "blackjack" }!!
        val player2Result = gameResult.playerResults.find { it.player.name == "win21" }!!
        val player3Result = gameResult.playerResults.find { it.player.name == "lose" }!!

        assertThat(player1Result.outcome).isEqualTo(Outcome.BLACKJACK)
        assertThat(player1Result.finalAmount).isEqualTo(25000)

        assertThat(player2Result.outcome).isEqualTo(Outcome.WIN)
        assertThat(player2Result.finalAmount).isEqualTo(20000)

        assertThat(player3Result.outcome).isEqualTo(Outcome.LOSE)
        assertThat(player3Result.finalAmount).isEqualTo(-10000)
    }
}
