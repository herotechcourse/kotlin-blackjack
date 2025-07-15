package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `should be BlackJack when cards are exactly 2 and total value is 21`() {
        val cardsInHand = Cards(mutableListOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.JACK, Suit.DIAMONDS)))
        assertThat(cardsInHand.hasBlackJack()).isTrue()
    }

    @Test
    fun `should not be BlackJack when total value is 21 but number of cards is different than 2`() {
        val cardsInHand = Cards(
            mutableListOf(
                Card(Rank.ACE, Suit.HEARTS),
                Card(Rank.NINE, Suit.DIAMONDS),
                Card(Rank.ACE, Suit.DIAMONDS)
            )
        )
        assertThat(cardsInHand.hasBlackJack()).isFalse()
    }
}
