package blackjack.model.participant

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {

    @ValueSource(strings = ["#####", "%23!", "m1112@", "111133/22"])
    @ParameterizedTest
    fun `player should have the correct name`(name: String) {
        assertThrows<IllegalArgumentException> {
            Player(name)
        }
    }
}
