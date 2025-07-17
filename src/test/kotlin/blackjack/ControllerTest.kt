package blackjack

import blackjack.controller.Controller
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ControllerTest {
    var controller = Controller()

    @ParameterizedTest
    @MethodSource("providePlayerInputs")
    fun `test player creation structure`(
        namesInput: String,
        betAmounts: List<Int>,
        expectedCount: Int,
        expectedNames: List<String>,
    ) {
        val players = controller.getPlayers()
        assertThat(players).hasSize(0)
    }

    companion object {
        @JvmStatic
        fun providePlayerInputs(): List<Arguments> {
            return listOf(
                Arguments.of("Alice", listOf(100), 1, listOf("Alice")),
                Arguments.of("Alice, Bob", listOf(100, 200), 2, listOf("Alice", "Bob")),
            )
        }
    }
}
