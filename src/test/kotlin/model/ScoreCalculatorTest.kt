package model

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ScoreCalculatorTest {
    @ParameterizedTest
    @MethodSource("cardProvider")
    fun `score for one card should return card value`(card: Card) {
        Assertions.assertThat(ScoreCalculator.getScore(setOf(card))).isEqualTo(Rank.score(card.rank))
    }

    @ParameterizedTest
    @MethodSource("setCardProvider")
    fun `score for set of cards should return total value of scores`(
        cards: Set<Card>,
        expectedTotal: Int,
    ) {
        Assertions.assertThat(ScoreCalculator.getScore(cards)).isEqualTo(expectedTotal)
    }

    companion object {
        @JvmStatic
        fun cardProvider() = listOf(Card(Rank.EIGHT, Suite.DIAMONDS), Card(Rank.QUEEN, Suite.DIAMONDS), Card(Rank.TWO, Suite.DIAMONDS))

        @JvmStatic
        fun setCardProvider(): List<Arguments> {
            return listOf(
                Arguments.of(
                    setOf(
                        Card(Rank.THREE, Suite.DIAMONDS),
                        Card(Rank.NINE, Suite.CLUBS),
                        Card(Rank.EIGHT, Suite.DIAMONDS),
                    ),
                    20,
                ),
                Arguments.of(
                    setOf(
                        Card(Rank.TWO, Suite.HEARTS),
                        Card(Rank.EIGHT, Suite.SPADES),
                        Card(Rank.ACE, Suite.CLUBS),
                    ),
                    21,
                ),
                Arguments.of(
                    setOf(
                        Card(Rank.ACE, Suite.DIAMONDS),
                        Card(Rank.ACE, Suite.CLUBS),
                        Card(Rank.ACE, Suite.HEARTS),
                    ),
                    13,
                ),
                Arguments.of(
                    setOf(
                        Card(Rank.ACE, Suite.CLUBS),
                        Card(Rank.ACE, Suite.HEARTS),
                        Card(Rank.ACE, Suite.SPADES),
                        Card(Rank.EIGHT, Suite.CLUBS),
                    ),
                    21,
                ),
                Arguments.of(
                    setOf(
                        Card(Rank.ACE, Suite.CLUBS),
                        Card(Rank.ACE, Suite.HEARTS),
                        Card(Rank.TEN, Suite.SPADES),
                        Card(Rank.TEN, Suite.CLUBS),
                    ),
                    22,
                ),
            )
        }
    }
}
