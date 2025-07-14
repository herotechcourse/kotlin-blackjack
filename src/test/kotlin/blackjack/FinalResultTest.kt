package blackjack

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.FinalResult
import blackjack.model.Gambler
import blackjack.model.GamblerInfo
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FinalResultTest {
    @Test
    fun `Player and Dealer gets blackjack`() {
        val dealer = Dealer(GamblerInfo("Dealer"))
        dealer.addCard(
            listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE)),
        )

        val player = Gambler(GamblerInfo("Player"))
        player.addCard(
            listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE)),
        )
        val finalResult = FinalResult(dealer, listOf(player))
        assertThat(finalResult.draw.size).isEqualTo(1)
    }

    @Test
    fun `Dealer gets blackjack and Player is below`() {
        val dealer = Dealer(GamblerInfo("Dealer"))
        dealer.addCard(
            listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE)),
        )

        val player = Gambler(GamblerInfo("Player"))
        player.addCard(
            listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.SIX, Suit.SPADE)),
        )
        val finalResult = FinalResult(dealer, listOf(player))
        assertThat(finalResult.lose.size).isEqualTo(1)
    }

    @Test
    fun `Dealer gets busted and Player is below 21`() {
        val dealer = Dealer(GamblerInfo("Dealer"))
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.KING, Suit.HEART),
                Card(Rank.KING, Suit.DIAMOND),
            ),
        )

        val player = Gambler(GamblerInfo("Player"))
        player.addCard(
            listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.SIX, Suit.SPADE)),
        )
        val finalResult = FinalResult(dealer, listOf(player))
        assertThat(finalResult.win.size).isEqualTo(1)
    }

    @Test
    fun `Player and Dealer gets busted`() {
        val dealer = Dealer(GamblerInfo("Dealer"))
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.KING, Suit.HEART),
                Card(Rank.KING, Suit.DIAMOND),
            ),
        )

        val player = Gambler(GamblerInfo("Player"))
        player.addCard(
            listOf(
                Card(Rank.TEN, Suit.SPADE),
                Card(Rank.TEN, Suit.HEART),
                Card(Rank.TEN, Suit.DIAMOND),
            ),
        )
        val finalResult = FinalResult(dealer, listOf(player))
        assertThat(finalResult.lose.size).isEqualTo(1)
    }

    @Test
    fun `Dealer is below player No Blackjack`() {
        val dealer = Dealer(GamblerInfo("Dealer"))
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.THREE, Suit.HEART),
            ),
        )

        val player = Gambler(GamblerInfo("Player"))
        player.addCard(
            listOf(
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.NINE, Suit.HEART),
            ),
        )
        val finalResult = FinalResult(dealer, listOf(player))
        assertThat(finalResult.win.size).isEqualTo(1)
    }

    @Test
    fun `Dealer is below and equal with player`() {
        val dealer = Dealer(GamblerInfo("Dealer"))
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.THREE, Suit.HEART),
            ),
        )

        val player = Gambler(GamblerInfo("Player"))
        player.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.THREE, Suit.HEART),
            ),
        )
        val finalResult = FinalResult(dealer, listOf(player))
        assertThat(finalResult.draw.size).isEqualTo(1)
    }

    @Test
    fun `Dealer is below 21 but greater than PLayer`() {
        val dealer = Dealer(GamblerInfo("Dealer"))
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.KING, Suit.HEART),
            ),
        )

        val player = Gambler(GamblerInfo("Player"))
        player.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.THREE, Suit.HEART),
            ),
        )
        val finalResult = FinalResult(dealer, listOf(player))
        assertThat(finalResult.lose.size).isEqualTo(1)
    }
}
