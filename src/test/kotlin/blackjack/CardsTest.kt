package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `test`() {
        val cards =
            Cards(
                hashSetOf(
                    Card(Symbol.HEART, 1),
                    Card(Symbol.HEART, 2),
                    Card(Symbol.HEART, 3),
                    Card(Symbol.HEART, 4),
                ),
            )
        val hand = Cards()
        val card = cards.hold.first()
        cards.move(hand)

        assertThat(hand.hold.last()).isEqualTo(card)
    }
}
