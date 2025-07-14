package service

import model.ResultStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ResultCalculatorTest {
    @ParameterizedTest
    @MethodSource("scoreProvider")
    fun `test players results`(
        playersScores: List<Int>,
        dealerScore: Int,
        expectedResultStatus: List<ResultStatus>,
    ) {
        assertThat(ResultCalculator.getResult(playersScores, dealerScore)).isEqualTo(expectedResultStatus)
    }

    companion object {
        @JvmStatic
        fun scoreProvider(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(
                        21,
                        23,
                        24,
                    ),
                    21,
                    listOf(ResultStatus.DRAW, ResultStatus.LOSS, ResultStatus.LOSS),
                ),
                Arguments.of(
                    listOf(
                        21,
                        18,
                        20,
                    ),
                    20,
                    listOf(ResultStatus.WIN, ResultStatus.LOSS, ResultStatus.DRAW),
                ),
                Arguments.of(
                    listOf(
                        12,
                        20,
                        17,
                    ),
                    23,
                    listOf(ResultStatus.WIN, ResultStatus.WIN, ResultStatus.WIN),
                ),
                Arguments.of(
                    listOf(
                        21,
                        17,
                        20,
                    ),
                    18,
                    listOf(ResultStatus.WIN, ResultStatus.LOSS, ResultStatus.WIN),
                ),
            )
        }
    }
}
