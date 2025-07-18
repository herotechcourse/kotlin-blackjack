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

    @Test
    fun `drawing a card over 21 leads to Bust state`() {
        val initial = Hit(Hand(listOf(TestCards.QUEEN, TestCards.JACK))) // 20
        val next = initial.draw(TestCards.FOUR) // 24 → Bust

        assertTrue(next is Bust)
    }

    @Test
    fun `calling stay returns Stay state`() {
        val initial = Hit(Hand(listOf(TestCards.QUEEN, TestCards.JACK)))
        val next = initial.stay()

        assertTrue(next is Stay)
    }
}
