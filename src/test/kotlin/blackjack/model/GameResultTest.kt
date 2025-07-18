package blackjack.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `should calculate earnings for blackjack win`() {
        val earnings = GameResult.BLACKJACK_WIN.calculateEarnings(10000)
        Assertions.assertEquals(15000, earnings)
    }

    @Test
    fun `should calculate earnings for regular win`() {
        val earnings = GameResult.WIN.calculateEarnings(10000)
        Assertions.assertEquals(10000, earnings)
    }

    @Test
    fun `should calculate earnings for draw`() {
        val earnings = GameResult.DRAW.calculateEarnings(10000)
        Assertions.assertEquals(0, earnings)
    }

    @Test
    fun `should calculate earnings for loss`() {
        val earnings = GameResult.LOSE.calculateEarnings(10000)
        Assertions.assertEquals(-10000, earnings)
    }
}
