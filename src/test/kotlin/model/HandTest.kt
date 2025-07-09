package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class HandTest {
    @Test
    fun `addCard adds a card to hand`() {
        val hand = Hand()
        val previousSize = hand.getCards().size
        hand.addCard(Card(Rank.THREE, Suite.HEARTS))
        assertThat(hand.getCards().size).isEqualTo(previousSize + 1)
    }

    @ParameterizedTest
    @MethodSource("setCardProvider")
    fun `test score on hand`(cards: Set<Card>) {
        val hand = Hand()
        cards.forEach { hand.addCard(it) }
        val previousScore = hand.scoreOnHand()
        val card = Card(Rank.THREE, Suite.HEARTS)
        hand.addCard(card)
        assertThat(previousScore + Rank.score(card.rank)).isEqualTo(hand.scoreOnHand())
    }

    companion object {
        @JvmStatic
        fun setCardProvider(): List<Set<Card>> {
            return listOf(
                setOf(
                    Card(Rank.THREE, Suite.DIAMONDS),
                    Card(Rank.NINE, Suite.CLUBS),
                    Card(Rank.TWO, Suite.DIAMONDS),
                ),
                setOf(
                    Card(Rank.TWO, Suite.HEARTS),
                    Card(Rank.EIGHT, Suite.SPADES),
                    Card(Rank.FIVE, Suite.CLUBS),
                ),
                setOf(
                    Card(Rank.TWO, Suite.DIAMONDS),
                    Card(Rank.THREE, Suite.CLUBS),
                    Card(Rank.FOUR, Suite.HEARTS),
                ),
                setOf(
                    Card(Rank.FIVE, Suite.CLUBS),
                    Card(Rank.SIX, Suite.HEARTS),
                    Card(Rank.TWO, Suite.SPADES),
                    Card(Rank.TWO, Suite.CLUBS),
                ),
            )
        }
    }
}
