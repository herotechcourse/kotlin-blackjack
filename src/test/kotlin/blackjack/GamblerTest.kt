package blackjack

import blackjack.model.Card
import blackjack.model.Gambler
import blackjack.model.GamblerInfo
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GamblerTest {
    @Test
    fun `return true if Player below Blackjack`() {
        val gambler = Gambler(GamblerInfo("Player"))
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.NINE, Suit.SPADE))
        gambler.addCard(cards)
        assertThat(gambler.isPlayerBelowBlackJack()).isTrue
    }

    @Test
    fun `return false if Player above or equal Blackjack`() {
        val gambler = Gambler(GamblerInfo("Player"))
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE))
        gambler.addCard(cards)
        assertThat(gambler.isPlayerBelowBlackJack()).isFalse
    }

    @Test
    fun `return true if Player has two cards`() {
        val gambler = Gambler(GamblerInfo("Player"))
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE))
        gambler.addCard(cards)
        assertThat(gambler.hasCardCount()).isTrue
    }

    @Test
    fun `return false if Player does not have two cards`() {
        val gambler = Gambler(GamblerInfo("Player"))
        gambler.addCard(listOf(Card(Rank.ACE, Suit.SPADE)))
        assertThat(gambler.hasCardCount()).isFalse
    }

    @Test
    fun `return negative winnings if lost`() {
        val gambler = Gambler(GamblerInfo("Player"))
        gambler.setBetAmount(10.0)
        gambler.calculateAndSetWinnings(false)
        assertThat(gambler.winnings).isEqualTo(-10.0)
    }

    @Test
    fun `return 1,5x winnings if BlackJack and Win`() {
        val gambler = Gambler(GamblerInfo("Player"))
        gambler.setBetAmount(10.0)
        gambler.addCard(
            listOf(
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.KING, Suit.SPADE),
            ),
        )
        gambler.calculateAndSetWinnings(true)

        assertThat(gambler.winnings).isEqualTo(15.0)
    }

    @Test
    fun `return 1x winnings if Win`() {
        val gambler = Gambler(GamblerInfo("Player"))
        gambler.setBetAmount(10.0)
        gambler.addCard(
            listOf(
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.THREE, Suit.SPADE),
            ),
        )
        gambler.calculateAndSetWinnings(true)

        assertThat(gambler.winnings).isEqualTo(10.0)
    }
}
