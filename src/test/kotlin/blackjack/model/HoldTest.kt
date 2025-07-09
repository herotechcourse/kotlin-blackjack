package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HoldTest {
    @Test
    fun `should be able to move a card`() {
        val hold =
            Hold(
                setOf(
                    Card(Suit.HEART, 1),
                    Card(Suit.HEART, 2),
                    Card(Suit.HEART, 3),
                    Card(Suit.HEART, 4),
                ),
            )
        val player = Player(Person("Mina"), Hand())
        hold.moveCard(player)

        assertThat(player.hand.numberOfCards()).isEqualTo(1)
    }

    @Test
    fun `should be able to move a card and remove that card from the cardDesk`() {
        val hold =
            Hold(
                setOf(
                    Card(Suit.HEART, 1),
                    Card(Suit.HEART, 2),
                    Card(Suit.HEART, 3),
                    Card(Suit.HEART, 4),
                ),
            )
        val player = Player(Person("Mina"), Hand())
        hold.moveCard(player)
        assertThat(hold.cards).hasSize(3)
    }

    @Test
    fun `order of cards in two cardDesks is different`() {
        val cardDesk1 = CardDeck().getCards()
        val cardDesk2 = CardDeck().getCards()

        assertThat(cardDesk1).isNotEqualTo(cardDesk2)
    }
}
