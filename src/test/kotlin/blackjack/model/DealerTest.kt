package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `shouldDrawCardOrNot() - return true if dealer's hand is less or equal to 16`() {
        val dealer = Dealer()
        dealer.drawCard(Card(Rank.SIX, Suit.DIAMONDS))
        dealer.drawCard(Card(Rank.TEN, Suit.DIAMONDS))

        assertEquals(true, dealer.shouldDrawCardOrNot())
    }

    @Test
    fun `shouldDrawCardOrNot() - return false if dealer's hand is bigger or equal to 17`() {
        val dealer = Dealer()
        dealer.drawCard(Card(Rank.SEVEN, Suit.DIAMONDS))
        dealer.drawCard(Card(Rank.TEN, Suit.DIAMONDS))

        assertEquals(false, dealer.shouldDrawCardOrNot())
    }
}
