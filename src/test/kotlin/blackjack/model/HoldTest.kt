package blackjack.model

import blackjack.model.card.Card
import blackjack.model.game.CardDeck
import blackjack.model.game.Hold
import blackjack.model.card.Rank
import blackjack.model.card.Suit
import blackjack.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HoldTest {
    @Test
    fun `should be able to move a card`() {
        val hold =
            Hold(
                setOf(
                    Card(Suit.HEART, Rank.ACE),
                    Card(Suit.HEART, Rank.TWO),
                    Card(Suit.HEART, Rank.THREE),
                    Card(Suit.HEART, Rank.FOUR),
                ),
            )
        val player = Player("Mina")
        hold.moveCard(player)

        assertThat(player.numberInHand()).isEqualTo(1)
    }

    @Test
    fun `should be able to move a card and remove that card from the cardDesk`() {
        val hold =
            Hold(
                setOf(
                    Card(Suit.HEART, Rank.ACE),
                    Card(Suit.HEART, Rank.TWO),
                    Card(Suit.HEART, Rank.THREE),
                    Card(Suit.HEART, Rank.FOUR),
                ),
            )
        val player = Player("Mina")
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
