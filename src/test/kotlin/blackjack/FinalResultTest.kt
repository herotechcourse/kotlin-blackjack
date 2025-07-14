package blackjack

import blackjack.model.Card
import blackjack.model.FinalResult
import blackjack.model.GamblerInfo
import blackjack.model.Player
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class FinalResultTest {
    @ParameterizedTest
    @MethodSource("provideTestCases")
    fun `test final result with various card combinations`(
        dealerCards: List<Card>,
        playerCards: List<Card>,
        expectedWin: Boolean,
        expectedLose: Boolean,
        expectedDraw: Boolean,
    ) {
        val dealer = Player(GamblerInfo("dealer"))
        val player = Player(GamblerInfo("player1"))

        dealer.addCard(dealerCards)
        player.addCard(playerCards)

        val finalResult = FinalResult(dealer, listOf(player))

        assertThat(finalResult.win.contains(player)).isEqualTo(expectedWin)
        assertThat(finalResult.lose.contains(player)).isEqualTo(expectedLose)
        assertThat(finalResult.draw.contains(player)).isEqualTo(expectedDraw)
    }

    companion object {
        @JvmStatic
        fun provideTestCases(): List<Arguments> {
            return listOf(
                // Dealer busts (25), player wins (19)
                Arguments.of(
                    listOf(Card(Rank.KING, Suit.SPADE), Card(Rank.QUEEN, Suit.HEART), Card(Rank.FIVE, Suit.DIAMOND)),
                    listOf(Card(Rank.TEN, Suit.SPADE), Card(Rank.NINE, Suit.HEART)),
                    true,
                    false,
                    false,
                ),
                // Player busts (25), dealer wins (17)
                Arguments.of(
                    listOf(Card(Rank.TEN, Suit.SPADE), Card(Rank.SEVEN, Suit.HEART)),
                    listOf(Card(Rank.KING, Suit.SPADE), Card(Rank.QUEEN, Suit.HEART), Card(Rank.FIVE, Suit.DIAMOND)),
                    false,
                    true,
                    false,
                ),
                // Player wins with higher score (19 > 17)
                Arguments.of(
                    listOf(Card(Rank.TEN, Suit.SPADE), Card(Rank.SEVEN, Suit.HEART)),
                    listOf(Card(Rank.TEN, Suit.SPADE), Card(Rank.NINE, Suit.HEART)),
                    true,
                    false,
                    false,
                ),
                // Dealer wins with higher score (19 > 17)
                Arguments.of(
                    listOf(Card(Rank.TEN, Suit.SPADE), Card(Rank.NINE, Suit.HEART)),
                    listOf(Card(Rank.TEN, Suit.SPADE), Card(Rank.SEVEN, Suit.HEART)),
                    false,
                    true,
                    false,
                ),
                // Draw with same score (18 = 18)
                Arguments.of(
                    listOf(Card(Rank.TEN, Suit.SPADE), Card(Rank.EIGHT, Suit.HEART)),
                    listOf(Card(Rank.NINE, Suit.SPADE), Card(Rank.NINE, Suit.HEART)),
                    false,
                    false,
                    true,
                ),
            )
        }
    }
}
