package blackjack

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.GamblerInfo
import blackjack.model.PlayerBet
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `return true if Dealer below MinScore`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.TWO, Suit.SPADE))
        dealer.addCard(cards)
        assertThat(dealer.isDealerBelowMinScore()).isTrue
    }

    @Test
    fun `returns false if Dealer above or equal MinScore`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.SPADE))
        dealer.addCard(cards)
        assertThat(dealer.isDealerBelowMinScore()).isFalse
    }

    @Test
    fun `returns the exact amount of winnings`() {
        val dealer = Dealer(GamblerInfo("Dealer"), PlayerBet())
        dealer.calculateAndSetWinnings(listOf(-20_00.0, 10_00.0))
        assertThat(dealer.winnings).isEqualTo(10_00.0)
    }
}
