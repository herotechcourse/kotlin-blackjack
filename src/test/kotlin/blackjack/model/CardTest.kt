package blackjack.model

import blackjack.view.RankView
import blackjack.view.SuitView
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardTest {
    @ParameterizedTest(name = "Card - create valid digit value from {0}")
    @ValueSource(strings = ["A♥", "3♣", "4♠", "5♥", "6♣", "7♥", "8♠", "9♣", "10♥", "J♦", "Q♣", "K♦", "3♥"])
    fun `Card create valid digit from name`(candidate: String) {
        val digit = candidate.dropLast(1)
        val suitSymbol = candidate.last()
        val rank = RankView.fromSymbol(digit)
        val suit = SuitView.fromSymbol(suitSymbol)
        val card = Card(rank, suit)

        assertEquals(digit, RankView.toSymbol(card.rank))
    }
}
