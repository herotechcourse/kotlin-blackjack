package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DealerTest {
//    @Test
//    fun `makeDecision returns true if score on hand is 16 or less`() {
//        val dealer = Dealer()
//
//    }

    @ParameterizedTest
    @MethodSource("setCardProvider")
    fun `makeDecision returns true if score on hand is 16 or less`(
        cards: Set<Card>,
        expectedDecision: Boolean,
    ) {
        val dealer = Dealer()
        cards.forEach { dealer.drawCard(it) }
        assertThat(dealer.makeDecision()).isEqualTo(expectedDecision)
    }

    companion object {
        @JvmStatic
        fun setCardProvider(): List<Arguments> {
            return listOf(
                Arguments.of(
                    setOf(
                        Card(Rank.THREE, Suite.DIAMONDS),
                        Card(Rank.NINE, Suite.CLUBS),
                        Card(Rank.EIGHT, Suite.DIAMONDS),
                    ),
                    false,
                ),
                Arguments.of(
                    setOf(
                        Card(Rank.TWO, Suite.HEARTS),
                        Card(Rank.EIGHT, Suite.SPADES),
                        Card(Rank.ACE, Suite.CLUBS),
                    ),
                    false,
                ),
                Arguments.of(
                    setOf(
                        Card(Rank.ACE, Suite.DIAMONDS),
                        Card(Rank.ACE, Suite.CLUBS),
                        Card(Rank.ACE, Suite.HEARTS),
                    ),
                    true,
                ),
                Arguments.of(
                    setOf(
                        Card(Rank.ACE, Suite.CLUBS),
                        Card(Rank.ACE, Suite.HEARTS),
                    ),
                    true,
                ),
            )
        }
    }
}
