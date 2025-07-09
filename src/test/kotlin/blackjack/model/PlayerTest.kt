package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class PlayerTest {
    val player = Player("Alex")

    @Test
    fun `should add cards to cardsInHand`() {
        val card = Card(Rank.KING, Suit.HEARTS)
        assertThat(player.cardsInHand).hasSize(0)
        player.drawCard(card)
        assertThat(player.cardsInHand).hasSize(1)
    }

    @Test
    fun `should calculate sum of values of cards in hand`() {
        val card1 = Card(Rank.TWO, Suit.HEARTS)
        val card2 = Card(Rank.THREE, Suit.HEARTS)
        player.drawCard(card1)
        player.drawCard(card2)
        assertThat(player.calculateTotalValueOfCards()).isEqualTo(5)
    }

    @Test
    fun `should change the activeStatus of player`() {
        assertThat(player.isActive).isTrue
        val totalValueOfCard = 25
        player.updateActiveStatus(totalValueOfCard)
        assertThat(player.isActive).isFalse
    }
}
