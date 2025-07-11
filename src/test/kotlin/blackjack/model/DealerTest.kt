package blackjack.model

import blackjack.Fixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `shouldDrawCardOrNot() - return true if dealer's hand is less or equal to 16`() {
        val dealer = Dealer()
        dealer.drawCard(Fixture.DIAMONDS_SIX)
        dealer.drawCard(Fixture.DIAMONDS_TEN)

        assertEquals(true, dealer.shouldDrawCardOrNot())
    }

    @Test
    fun `shouldDrawCardOrNot() - return false if dealer's hand is bigger or equal to 17`() {
        val dealer = Dealer()
        dealer.drawCard(Fixture.DIAMONDS_SEVEN)
        dealer.drawCard(Fixture.DIAMONDS_TEN)

        assertEquals(false, dealer.shouldDrawCardOrNot())
    }
}
