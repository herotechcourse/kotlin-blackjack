package blackjack

import blackjack.model.GamblerInfo
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class GamblerInfoTest {
    @ParameterizedTest
    @MethodSource("invalidNames")
    fun `should throw exception - invalid names`(name: String) {
        assertThrows<IllegalArgumentException> {
            GamblerInfo(name)
        }
    }

    @ParameterizedTest
    @MethodSource("validNames")
    fun `should not throw exception - valid names`(name: String) {
        assertDoesNotThrow { GamblerInfo(name) }
    }

    companion object {
        @JvmStatic
        fun invalidNames() = listOf("", "     ")

        @JvmStatic
        fun validNames() = listOf("Jin", "Jenny", "Jay", "J", "jjj", "jjjjjjjjjjjjjjjj")
    }
}
