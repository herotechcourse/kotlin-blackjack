package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardTest {
    @ParameterizedTest(name = "Card - create valid digit value from {0}")
    @ValueSource(strings = ["A♥", "3♣", "4♠", "5♥", "6♣", "7♥", "8♠", "9♣", "10♥", "J♦", "Q♣", "K♦", "3♥"])
    fun `Card create valid digit from name`(candidate: String) {
        val card = Card(candidate)
        assertEquals(candidate.substring(0, candidate.length - 1), card.digit)
    }
}
