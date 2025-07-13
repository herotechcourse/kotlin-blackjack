package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun calculatePoints() {
        val hand: Hand = Hand(emptyList()) + TestCards.Ace + TestCards.TWO + TestCards.JACK
        assertThat(hand.calculatePoints()).isEqualTo(13)
    }

    @Test
    fun calculatePoints2() {
        val hand: Hand = Hand(emptyList()) + TestCards.Ace + TestCards.JACK
        assertThat(hand.calculatePoints()).isEqualTo(21)
    }

    @Test
    fun calculatePoints3() {
        val hand: Hand = Hand(emptyList()) + TestCards.Ace + TestCards.Ace + TestCards.Ace + TestCards.FOUR
        assertThat(hand.calculatePoints()).isEqualTo(17)
    }
}
