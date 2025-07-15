package blackjack

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Gambler
import blackjack.model.GamblerInfo
import blackjack.model.PlayerBet
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GamblerTest {
    @Test
    fun `isPlayerBelowBlackJack() return true if Player below Blackjack`() {
        val gambler = Gambler(GamblerInfo("Player"), PlayerBet())
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.NINE, Suit.SPADE))
        gambler.addCard(cards)
        assertThat(gambler.isPlayerBelowBlackJack()).isTrue
    }

    @Test
    fun `isPlayerBelowBlackJack() return false if Player above or equal Blackjack`() {
        val gambler = Gambler(GamblerInfo("Player"), PlayerBet())
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE))
        gambler.addCard(cards)
        assertThat(gambler.isPlayerBelowBlackJack()).isFalse
    }

    @Test
    fun `hasCardCount() return true if Player has two cards`() {
        val gambler = Gambler(GamblerInfo("Player"), PlayerBet())
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE))
        gambler.addCard(cards)
        assertThat(gambler.hasCardCount()).isTrue
    }

    @Test
    fun `hasCardCount() return false if Player does not have two cards`() {
        val gambler = Gambler(GamblerInfo("Player"), PlayerBet())
        gambler.addCard(listOf(Card(Rank.ACE, Suit.SPADE)))
        assertThat(gambler.hasCardCount()).isFalse
    }

    @Test
    fun `Player and Dealer gets blackjack`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        dealer.addCard(
            listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE)),
        )

        val player = Gambler(GamblerInfo("Player"), PlayerBet(10.0))
        player.addCard(
            listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE)),
        )
        player.setWinnings(dealer)
        assertThat(player.winnings).isEqualTo(0.0)
    }

    @Test
    fun `Dealer gets blackjack and Player is below`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        dealer.addCard(
            listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE)),
        )

        val player = Gambler(GamblerInfo("Player"), PlayerBet(10.0))
        player.addCard(
            listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.SIX, Suit.SPADE)),
        )
        player.setWinnings(dealer)
        assertThat(player.winnings).isEqualTo(-10.0)
    }

    @Test
    fun `Dealer gets busted and Player is below 21`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.KING, Suit.HEART),
                Card(Rank.KING, Suit.DIAMOND),
            ),
        )

        val player = Gambler(GamblerInfo("Player"), PlayerBet(10.0))
        player.addCard(
            listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.SIX, Suit.SPADE)),
        )
        player.setWinnings(dealer)
        assertThat(player.winnings).isEqualTo(10.0)
    }

    @Test
    fun `Player and Dealer gets busted`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.KING, Suit.HEART),
                Card(Rank.KING, Suit.DIAMOND),
            ),
        )

        val player = Gambler(GamblerInfo("Player"), PlayerBet(10.0))
        player.addCard(
            listOf(
                Card(Rank.TEN, Suit.SPADE),
                Card(Rank.TEN, Suit.HEART),
                Card(Rank.TEN, Suit.DIAMOND),
            ),
        )
        player.setWinnings(dealer)
        assertThat(player.winnings).isEqualTo(-10.0)
    }

    @Test
    fun `Dealer is below player No Blackjack`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.THREE, Suit.HEART),
            ),
        )

        val player = Gambler(GamblerInfo("Player"), PlayerBet(10.0))
        player.addCard(
            listOf(
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.NINE, Suit.HEART),
            ),
        )
        player.setWinnings(dealer)
        assertThat(player.winnings).isEqualTo(10.0)
    }

    @Test
    fun `Dealer is below 21 and equal with player`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.THREE, Suit.HEART),
            ),
        )

        val player = Gambler(GamblerInfo("Player"), PlayerBet(10.0))
        player.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.THREE, Suit.HEART),
            ),
        )
        player.setWinnings(dealer)
        assertThat(player.winnings).isEqualTo(0.0)
    }

    @Test
    fun `Dealer is below 21 but greater than PLayer`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.KING, Suit.HEART),
            ),
        )

        val player = Gambler(GamblerInfo("Player"), PlayerBet(10.0))
        player.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.THREE, Suit.HEART),
            ),
        )
        player.setWinnings(dealer)
        assertThat(player.winnings).isEqualTo(-10.0)
    }

    @Test
    fun `Dealer is below 21 but Player is BlackJack`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.KING, Suit.HEART),
            ),
        )

        val player = Gambler(GamblerInfo("Player"), PlayerBet(10.0))
        player.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.ACE, Suit.HEART),
            ),
        )
        player.setWinnings(dealer)
        assertThat(player.winnings).isEqualTo(15.0)
    }

    @Test
    fun `Dealer is busted and Player is BlackJack`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.KING, Suit.HEART),
                Card(Rank.KING, Suit.DIAMOND),
            ),
        )

        val player = Gambler(GamblerInfo("Player"), PlayerBet())
        player.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.ACE, Suit.HEART),
            ),
        )
        player.setWinnings(dealer)
        assertThat(player.winnings).isEqualTo(15.0)
    }
}
