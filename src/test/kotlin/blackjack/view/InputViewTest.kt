package blackjack.view

import blackjack.view.InputView.Parser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputViewTest {
    @Test
    fun `name should be parsed correctly`() {
        val input = "mina, guri, ella"
        val parsed = Parser.playerNames(input)
        val expected = listOf("mina", "guri", "ella")

        assertEquals(parsed, expected)
    }

    @ValueSource(strings = ["#####", "%23!", "m1112@", "111133/22", "#####, %23!", "#####, %23!"])
    @ParameterizedTest
    fun `throw if name format is not correct`(input: String) {
        assertThrows<IllegalArgumentException> {
            Parser.playerNames(input)
        }
    }
}
