package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `should return a correct`() {
        val hand = Hand()
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.FIVE))
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.ACE))
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.ACE))
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.FOUR))
        println(hand.getScore())
        assertThat(hand.getScore())
    }

    @Test
    fun `should isBlackjack() return true if the score is 21`() {
        val hand = Hand()
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.KING))
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.ACE))
        assertThat(hand.isBlackjack()).isTrue()
    }

    @Test
    fun `should isBusts() return true if the score over 21`() {
        val hand = Hand()
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.KING))
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.KING))
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.KING))
        assertThat(hand.isBusts()).isTrue()
    }

    @Test
    fun `should isBlackjack() return false if the score is not 21`() {
        val hand = Hand()
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.KING))
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.KING))
        assertThat(hand.isBlackjack()).isFalse()
    }

    @Test
    fun `should isBusts() return false if the score not over 21`() {
        val hand = Hand()
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.KING))
        hand.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.ACE))
        assertThat(hand.isBusts()).isFalse()
    }
}