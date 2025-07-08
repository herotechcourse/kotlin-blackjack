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

    @Test
    fun `should be able to move a card and remove that card from the cardDesk`() {
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
        cards.move(hand)
        assertThat(cards.hold).hasSize(3)
    }

    @Test
    fun `order of cards in two cardDesks is different`() {
        val cardDesk1 = CardDeck().cards.hold.toList()
        val cardDesk2 = CardDeck().cards.hold.toList()

        assertThat(cardDesk1).isNotEqualTo(cardDesk2)
    }
}
