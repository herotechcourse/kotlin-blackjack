package blackjack

import blackjack.model.Card
import blackjack.model.Rank
import blackjack.model.Suit
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class FinalResultTest {
    @ParameterizedTest
    @MethodSource("getCaseOfThreePlayers")
    fun `test three players payout scenarios`(
        dealerCards: List<Card>,
        p1Cards: List<Card>,
        p2Cards: List<Card>,
        p3Cards: List<Card>,
        p1Bet: Int,
        p2Bet: Int,
        p3Bet: Int,
        expectedEarnings: Map<String, Int>,
        expectedDealerEarnings: Int,
    ) {
    }

    companion object {
        @JvmStatic
        fun getCaseOfThreePlayers(): List<Arguments> {
            return listOf(
                // Two blackjack winners, one normal winner (dealer busts)
                Arguments.of(
                    listOf<Card>(
                        Card(Rank.KING, Suit.SPADE),
                        Card(Rank.QUEEN, Suit.HEART),
                        Card(Rank.FIVE, Suit.DIAMOND),
                    ),
                    listOf<Card>(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.HEART)),
                    listOf<Card>(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.QUEEN, Suit.SPADE)),
                    listOf<Card>(Card(Rank.TEN, Suit.SPADE), Card(Rank.NINE, Suit.HEART)),
                    100, 200, 150,
                    mapOf(
                        "p1" to 150,
                        "p2" to 300,
                        "p3" to 150,
                    ),
                    -600,
                ),
                // Two normal winners, one lose
                Arguments.of(
                    listOf<Card>(Card(Rank.TEN, Suit.SPADE), Card(Rank.SEVEN, Suit.HEART)),
                    listOf<Card>(Card(Rank.TEN, Suit.DIAMOND), Card(Rank.NINE, Suit.SPADE)),
                    listOf<Card>(Card(Rank.TEN, Suit.HEART), Card(Rank.EIGHT, Suit.CLUB)),
                    listOf<Card>(Card(Rank.TEN, Suit.CLUB), Card(Rank.FIVE, Suit.DIAMOND)),
                    100, 200, 150,
                    mapOf(
                        "p1" to 100,
                        "p2" to 200,
                        "p3" to -150,
                    ),
                    -150,
                ),
                // Two lose, one tie
                Arguments.of(
                    listOf<Card>(Card(Rank.TEN, Suit.SPADE), Card(Rank.NINE, Suit.HEART)),
                    listOf<Card>(Card(Rank.TEN, Suit.DIAMOND), Card(Rank.SEVEN, Suit.SPADE)),
                    listOf<Card>(Card(Rank.KING, Suit.HEART), Card(Rank.QUEEN, Suit.CLUB), Card(Rank.FIVE, Suit.DIAMOND)),
                    listOf<Card>(Card(Rank.TEN, Suit.CLUB), Card(Rank.NINE, Suit.DIAMOND)),
                    100, 200, 150,
                    mapOf(
                        "p1" to -100,
                        "p2" to -200,
                        "p3" to 0,
                    ),
                    300,
                ),
            )
        }

        fun getCaseOfTwoPlayers(): List<Arguments> {
            return listOf(
                // One blackjack winner, one normal winner (dealer busts)
                Arguments.of(
                    listOf<Card>(
                        Card(Rank.KING, Suit.SPADE),
                        Card(Rank.QUEEN, Suit.HEART),
                        Card(Rank.FIVE, Suit.DIAMOND),
                    ),
                    listOf<Card>(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.HEART)),
                    listOf<Card>(Card(Rank.TEN, Suit.SPADE), Card(Rank.NINE, Suit.HEART)),
                    100,
                    200,
                    mapOf(
                        "p1" to 150,
                        "p2" to 200,
                    ),
                    -350,
                ),
                // One normal winner, one lose
                Arguments.of(
                    listOf<Card>(Card(Rank.TEN, Suit.SPADE), Card(Rank.EIGHT, Suit.HEART)),
                    listOf<Card>(Card(Rank.TEN, Suit.DIAMOND), Card(Rank.NINE, Suit.SPADE)),
                    listOf<Card>(Card(Rank.TEN, Suit.HEART), Card(Rank.SEVEN, Suit.CLUB)),
                    100,
                    200,
                    mapOf(
                        "p1" to 100,
                        "p2" to -200,
                    ),
                    100,
                ),
                // One normal winner, one tie
                Arguments.of(
                    listOf<Card>(Card(Rank.TEN, Suit.SPADE), Card(Rank.EIGHT, Suit.HEART)),
                    listOf<Card>(Card(Rank.TEN, Suit.DIAMOND), Card(Rank.NINE, Suit.SPADE)),
                    listOf<Card>(Card(Rank.TEN, Suit.HEART), Card(Rank.EIGHT, Suit.CLUB)),
                    100,
                    200,
                    mapOf(
                        "p1" to 100,
                        "p2" to 0,
                    ),
                    -100,
                ),
                // Both players have blackjack, dealer doesn't (should get 1.5x)
                Arguments.of(
                    listOf<Card>(Card(Rank.TEN, Suit.SPADE), Card(Rank.NINE, Suit.HEART)),
                    listOf<Card>(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.HEART)),
                    listOf<Card>(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.QUEEN, Suit.SPADE)),
                    100,
                    200,
                    mapOf(
                        "p1" to 150,
                        "p2" to 300,
                    ),
                    -450,
                ),
                // Both players have blackjack, dealer also has blackjack (should get 1x)
                Arguments.of(
                    listOf<Card>(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.HEART)),
                    listOf<Card>(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.QUEEN, Suit.SPADE)),
                    listOf<Card>(Card(Rank.ACE, Suit.HEART), Card(Rank.JACK, Suit.CLUB)),
                    100,
                    200,
                    mapOf(
                        "p1" to 100,
                        "p2" to 200,
                    ),
                    -300,
                ),
            )
        }
    }
}
