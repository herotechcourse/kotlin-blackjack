package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BettingInfoTest {

    @Test
    fun `should store player name and betting amount`() {
        val bettingInfo = BettingInfo("pobi", 10000)

        assertEquals("pobi", bettingInfo.playerName)
        assertEquals(10000, bettingInfo.amount)
    }
}
