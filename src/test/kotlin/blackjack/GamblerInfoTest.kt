package blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class GamblerInfoTest {
    @Test
    fun `should throw exception - empty input`() {
        assertThrows<IllegalArgumentException> {
            GamblerInfo("")
        }
    }

    @Test
    fun `should throw exception - blank input`() {
        assertThrows<IllegalArgumentException> {
            GamblerInfo("    ")
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["dealer", "DEALER", "deaLEr"])
    fun `should throw exception - using Reserved word`(name: String) {
        assertThrows<IllegalArgumentException> {
            GamblerInfo(name)
        }
    }

    @Test
    fun `should accept - normal name`() {
        assertEquals(GamblerInfo("Name").name, "Name")
    }
}
