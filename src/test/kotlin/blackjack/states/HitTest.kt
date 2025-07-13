package blackjack.states

import blackjack.TestCards
import blackjack.model.Hand
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `drawing a card under 21 keeps player in Hit state`() {
        val initial = Hit(Hand(listOf(TestCards.TWO, TestCards.FOUR))) // total = 6
        val next = initial.draw(TestCards.QUEEN) // total = 16

        assertTrue(next is Hit)
    }
}
