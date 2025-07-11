package blackjack.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackGameTest {
    @Test
    fun retryUntilSuccessTest()
    {
        var counter = 0
        BlackJackGame.retryUntilSuccess {
            counter++
            if (counter != 5) throw IllegalArgumentException()
        }

        assertThat(counter).isEqualTo(5)
    }

}