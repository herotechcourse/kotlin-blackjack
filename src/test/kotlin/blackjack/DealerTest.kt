package blackjack

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {

    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        player = Player("TestPlayer")
    }
    @Test
    fun `should give one card to player`() {
        val initialSize = player.getHand().size
        dealer.giveCardTo(player)
        val newSize = player.getHand().size
        assertEquals(initialSize + 1, newSize)
    }
}
