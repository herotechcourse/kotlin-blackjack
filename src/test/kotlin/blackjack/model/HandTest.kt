package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun calculatePoints() {
        val player = Player("doggo")
        val listOfCards =
            listOf(
                Card(Suit.DIAMONDS, 1),
                Card(Suit.DIAMONDS, 2),
                Card(Suit.DIAMONDS, 12),
                Card(Suit.DIAMONDS, 4),
            )
        listOfCards.forEach { player.addCard(it) }
        assertThat(player.calculatePoints()).isEqualTo(17)
    }

    @Test
    fun hasPoints2() {
        val player = Player("doggo")
        val listOfCards =
            listOf(
                Card(Suit.DIAMONDS, 1),
                Card(Suit.DIAMONDS, 11),
            )
        listOfCards.forEach { player.addCard(it) }
        assertThat(player.calculatePoints()).isEqualTo(21)
    }

    @Test
    fun hasPoints3() {
        val player = Player("doggo")
        val listOfCards =
            listOf(
                Card(Suit.DIAMONDS, 1),
                Card(Suit.HEART, 1),
                Card(Suit.SPADES, 1),
                Card(Suit.DIAMONDS, 11),
            )
        listOfCards.forEach { player.addCard(it) }
        assertThat(player.calculatePoints()).isEqualTo(13)
    }

    @Test
    fun hasPoints4() {
        val player = Player("doggo")
        val listOfCards =
            listOf(
                Card(Suit.DIAMONDS, 1),
                Card(Suit.HEART, 1),
                Card(Suit.SPADES, 1),
                Card(Suit.DIAMONDS, 7),
            )
        listOfCards.forEach { player.addCard(it) }
        assertThat(player.calculatePoints()).isEqualTo(20)
    }
}
