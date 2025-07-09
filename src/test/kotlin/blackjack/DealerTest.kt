package blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `giveCardTo should add one card to participant's hand`() {
        val dealer = Dealer()
        val player = Player("John")
        assertEquals(0,player.getHand().size)

        dealer.giveCardTo(player)

        assertEquals(1,player.getHand().size)
    }
}