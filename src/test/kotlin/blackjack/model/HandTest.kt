package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `should return a correct hand`() {
        val hand = Hand()
        hand.plus(Card(suit = Suit.DIAMONDS, rank = Rank.FIVE))
        hand.plus(Card(Suit.DIAMONDS, Rank.ACE))
        hand.plus(Card(Suit.DIAMONDS, Rank.ACE))
        hand.plus(Card(Suit.DIAMONDS, Rank.FOUR))
        println(hand.calculateSum())
        assertThat(hand.calculateSum())
    }

    @Test
    fun `size property should return correct number of cards`() {
        val hand1 = Hand()
        assertThat(hand1.size).isEqualTo(0)

        val hand2 = Hand(listOf(Card(Suit.HEARTS, Rank.ACE)))
        assertThat(hand2.size).isEqualTo(1)

        val hand3 = Hand(listOf(Card(Suit.HEARTS, Rank.TEN), Card(Suit.SPADES, Rank.QUEEN)))
        assertThat(hand3.size).isEqualTo(2)
    }

    @Test
    fun `plus should add a card to cards and return a new Hand`() {
        val card1 = Card(suit = Suit.HEARTS, rank = Rank.FIVE)
        val card2 = Card(suit = Suit.HEARTS, rank = Rank.SIX)

        val hand = Hand(listOf(card1, card2))
        val card3 = Card(suit = Suit.SPADES, rank = Rank.TEN)
        val newHand = hand + card3
        assertThat(newHand.cards).hasSize(3)
        assertThat(newHand.cards).containsExactlyInAnyOrder(card1, card2, card3)
    }

    @Test
    fun `calculateSum should return the sum of cards`() {
        val hand1 = Hand(listOf(Card(suit = Suit.HEARTS, rank = Rank.TEN), Card(suit = Suit.SPADES, rank = Rank.FIVE)))
        assertThat(hand1.calculateSum()).isEqualTo(15)

        val hand2 = Hand(listOf(Card(suit = Suit.HEARTS, rank = Rank.ACE), Card(suit = Suit.SPADES, rank = Rank.SIX)))
        assertThat(hand2.calculateSum()).isEqualTo(17)

        val hand3 = Hand(listOf(Card(Suit.CLUBS, Rank.ACE), Card(Suit.HEARTS, Rank.QUEEN), Card(Suit.DIAMONDS, Rank.KING)))
        assertThat(hand3.calculateSum()).isEqualTo(21)
    }
}
