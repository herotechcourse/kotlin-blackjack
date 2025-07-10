package blackjack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

    @Test
    fun `should accept - normal name`() {
        assertEquals(GamblerInfo("Name").name, "Name")
    }
}
