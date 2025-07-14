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
}
