package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
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


}