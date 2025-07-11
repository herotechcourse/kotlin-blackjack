package blackjack

import blackjack.model.GamblerInfo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GamblerInfoTest {
    @ParameterizedTest
    @ValueSource(strings = ["", "    "])
    fun `should throw exception - empty input`() {
        assertThrows<IllegalArgumentException> {
            GamblerInfo("")
        }
    }

    @Test
    fun `should accept - normal name`() {
        assertEquals(GamblerInfo("Name").name, "Name")
    }
}
