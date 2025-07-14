package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    val player = Player("Alex")

    @Test
    fun `should add cards to cardsInHand`() {
        val card = Card(Rank.KING, Suit.HEARTS)
        assertThat(player.cardsInHand.cards).hasSize(0)
        player.drawCard(card)
        assertThat(player.cardsInHand.cards).hasSize(1)
    }

    @Test
    fun `should calculate right sum of values when contain ACEs in cards`() {
        val card1 = Card(Rank.ACE, Suit.HEARTS)
        val card2 = Card(Rank.ACE, Suit.DIAMONDS)
        val card3 = Card(Rank.FIVE, Suit.DIAMONDS)
        player.drawCard(card1)
        player.drawCard(card2)
        player.drawCard(card3)
        player.cardsInHand.calculateTotalValueOfCards()
        assertThat(player.cardsInHand.calculateTotalValueOfCards()).isEqualTo(17)
    }

    @Test
    fun `should calculate sum of values of cards in hand`() {
        val card1 = Card(Rank.TWO, Suit.HEARTS)
        val card2 = Card(Rank.THREE, Suit.HEARTS)
        player.drawCard(card1)
        player.drawCard(card2)
        assertThat(player.cardsInHand.calculateTotalValueOfCards()).isEqualTo(5)
    }

    @Test
    fun `should change the playingStatus of player when total value exceed 21`() {
        assertThat(player.isPlaying).isTrue
        val card1 = Card(Rank.TEN, Suit.HEARTS)
        val card2 = Card(Rank.JACK, Suit.HEARTS)
        val card3 = Card(Rank.QUEEN, Suit.HEARTS)
        player.drawCard(card1)
        player.drawCard(card2)
        player.drawCard(card3)
        player.updatePlayingStatus(player.cardsInHand.isBustHand())
        assertThat(player.isPlaying).isFalse
    }

    @Test
    fun `should change playingStatus of player when total value is less than dealers points`() {
        assertThat(player.isPlaying).isTrue
        val card1 = Card(Rank.TEN, Suit.HEARTS)
        val card2 = Card(Rank.TWO, Suit.HEARTS)
        player.drawCard(card1)
        player.drawCard(card2)
        player.updatePlayingStatus(player.hasLessPointsThanDealer(13))
        assertThat(player.isPlaying).isFalse
    }
}
