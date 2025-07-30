package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `dealer should draw two cards`() {
        val deck = Deck()
        val dealer = Dealer()
        val prevHandSize = dealer.getCardsNumber()
        dealer.turn(deck, { }, dummyDecision())
        assertThat(dealer.getCardsNumber() - prevHandSize).isEqualTo(N)
    }

    companion object {
        const val N = 2

        fun dummyDecision(): (BasePlayer) -> Boolean {
            var counter = N
            return { player: BasePlayer ->
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
