package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `should be able to move a card`() {
        val cards =
            Cards(
                setOf(
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
