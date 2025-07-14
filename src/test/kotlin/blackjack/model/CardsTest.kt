package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `should return true when cards in hand have condition for BlackJack`() {
        val cardsInHand = Cards(mutableListOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.JACK, Suit.DIAMONDS)))
        assertThat(cardsInHand.checkIfHasBlackJack()).isTrue()
    }

    @Test
    fun `should return false when cards in hand dont fulfil condition for BlackJack`() {
        val cardsInHand = Cards(
            mutableListOf(
                Card(Rank.ACE, Suit.HEARTS),
                Card(Rank.NINE, Suit.DIAMONDS),
                Card(Rank.ACE, Suit.DIAMONDS)
            )
        )
        assertThat(cardsInHand.checkIfHasBlackJack()).isFalse()
    }
}
