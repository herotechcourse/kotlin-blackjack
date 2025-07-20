package model

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ResultCalculatorTest {
    @ParameterizedTest
    @MethodSource("scoreProvider")
    fun `test player earning ratio`(
        playerScore: Int,
        dealerScore: Int,
        expectedRatio: Double,
    ) {
        Assertions.assertThat(ResultCalculator.ratio(playerScore, dealerScore)).isEqualTo(expectedRatio)
    }

    companion object {
        @JvmStatic
        fun scoreProvider(): List<Arguments> {
            return listOf(
                Arguments.of(21, 21, 0.0),
                Arguments.of(23, 20, -1),
                Arguments.of(20, 23, 1),
                Arguments.of(21, 15, 1.5),
                Arguments.of(12, 15, -1.0),
                Arguments.of(17, 15, 1.0),
            )
        }
    }
}
