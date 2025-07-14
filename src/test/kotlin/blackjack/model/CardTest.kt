package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CardTest {
    companion object {
        @JvmStatic
        fun cardProvider() =
            listOf(
                Arguments.of(Rank.ACE, Suit.HEARTS, "A♥"),
                Arguments.of(Rank.THREE, Suit.CLUBS, "3♣"),
                Arguments.of(Rank.FOUR, Suit.SPADES, "4♠"),
                Arguments.of(Rank.FIVE, Suit.HEARTS, "5♥"),
                Arguments.of(Rank.SIX, Suit.CLUBS, "6♣"),
                Arguments.of(Rank.SEVEN, Suit.HEARTS, "7♥"),
                Arguments.of(Rank.EIGHT, Suit.SPADES, "8♠"),
                Arguments.of(Rank.NINE, Suit.CLUBS, "9♣"),
                Arguments.of(Rank.TEN, Suit.HEARTS, "10♥"),
                Arguments.of(Rank.JACK, Suit.DIAMONDS, "J♦"),
                Arguments.of(Rank.QUEEN, Suit.CLUBS, "Q♣"),
                Arguments.of(Rank.KING, Suit.DIAMONDS, "K♦"),
                Arguments.of(Rank.THREE, Suit.HEARTS, "3♥"),
            )
    }

    @ParameterizedTest(name = "Card - create valid card {2} from rank {0} and suit {1}")
    @MethodSource("cardProvider")
    fun `Card creates valid string representation`(
        rank: Rank,
        suit: Suit,
        expected: String,
    ) {
        val card = Card(rank, suit)
        assertEquals(expected, card.toString())
    }
}
