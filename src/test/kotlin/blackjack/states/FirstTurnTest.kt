package blackjack.states

import blackjack.model.TestCards
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FirstTurnTest {
    @Test
    fun `if hands has less then 2 cards remain in state`() {
        val state = FirstTurn()
        Assertions.assertTrue(state is FirstTurn)
    }

    @Test
    fun `draw one card keeps state in FirstTurn`() {
        val state = FirstTurn()
        val next = state.draw(TestCards.Ace)
        assertTrue(next is FirstTurn)
    }
}
