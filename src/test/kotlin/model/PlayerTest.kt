package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `player should draw two cards`() {
        val deck = Deck()
        val player = Player("pobi")
        val prevHandSize = player.getCardsNumber()
        player.turn(deck, { }, dummyDecision())
        assertThat(player.getCardsNumber() - prevHandSize).isEqualTo(N)
    }

    companion object {
        const val N = 2

        fun dummyDecision(): (BasePlayer) -> Boolean {
            var counter = N
            return { _: BasePlayer ->
                if (counter > 0) {
                    counter--
                    true
                } else {
                    false
                }
            }
        }
    }
}
