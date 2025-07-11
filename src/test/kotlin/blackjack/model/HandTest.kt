package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.card.Suit
import blackjack.model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun calculatePoints() {
        val player = Player("doggo")
        val listOfCards =
            listOf(
                Card(Suit.DIAMONDS, Rank.ACE),
                Card(Suit.DIAMONDS, Rank.TWO),
                Card(Suit.DIAMONDS, Rank.QUEEN),
                Card(Suit.DIAMONDS, Rank.FOUR),
            )
        listOfCards.forEach { player.addCard(it) }
        assertThat(player.calculatePoints()).isEqualTo(17)
    }

    @Test
    fun hasPoints2() {
        val player = Player("doggo")
        val listOfCards =
            listOf(
                Card(Suit.DIAMONDS, Rank.ACE),
                Card(Suit.DIAMONDS, Rank.JACK),
            )
        listOfCards.forEach { player.addCard(it) }
        assertThat(player.calculatePoints()).isEqualTo(21)
    }

    @Test
    fun hasPoints3() {
        val player = Player("doggo")
        val listOfCards =
            listOf(
                Card(Suit.DIAMONDS, Rank.ACE),
                Card(Suit.HEART, Rank.ACE),
                Card(Suit.SPADES, Rank.ACE),
                Card(Suit.DIAMONDS, Rank.JACK),
            )
        listOfCards.forEach { player.addCard(it) }
        assertThat(player.calculatePoints()).isEqualTo(13)
    }

    @Test
    fun hasPoints4() {
        val player = Player("doggo")
        val listOfCards =
            listOf(
                Card(Suit.DIAMONDS, Rank.ACE),
                Card(Suit.HEART, Rank.ACE),
                Card(Suit.SPADES, Rank.ACE),
                Card(Suit.DIAMONDS, Rank.SEVEN),
            )
        listOfCards.forEach { player.addCard(it) }
        assertThat(player.calculatePoints()).isEqualTo(20)
    }
}
