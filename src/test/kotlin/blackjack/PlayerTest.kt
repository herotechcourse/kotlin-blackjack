package blackjack

import blackjack.model.Card
import blackjack.model.Deck
import blackjack.model.GamblerInfo
import blackjack.model.Player
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {
    @Test
    fun `should be equal result`() {
        val card = Card(Rank.TEN, Suit.SPADE)
        val player = Player(GamblerInfo("Player"))
        player.addCard(listOf(card))
        assertEquals(10, player.score)
    }

    @Test
    fun `Ace card is considered one if score crosses 21`() {
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val player = Player(GamblerInfo("Player"))
        player.addCard(cards)
        assertEquals(12, player.score)
    }

    @Test
    fun `Ace card is considered 11 if the new score is less than 21`() {
        val cards = listOf(Card(Rank.FOUR, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val player = Player(GamblerInfo("Player"))
        player.addCard(cards)
        assertEquals(15, player.score)
    }

    @Test
    fun `should return 13 - Ace's rank is converted to 1 if score greater than 21`() {
        val cards =
            listOf(
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.KING, Suit.HEART),
            )
        val player = Player(GamblerInfo("Player"))
        player.addCard(cards)
        assertEquals(13, player.score)
    }

    @Test
    fun `should return 12 - both Ace's rank is converted to 1 if score greater than 21`() {
        val cards =
            listOf(
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.KING, Suit.HEART),
            )
        val player = Player(GamblerInfo("Player"))
        player.addCard(cards)
        assertEquals(12, player.score)
    }

    @Test
    fun `should return 22 - each Ace rank is converted to 1 if score greater than 21`() {
        val cards =
            listOf(
                Card(Rank.NINE, Suit.SPADE),
                Card(Rank.NINE, Suit.HEART),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.ACE, Suit.CLUB),
                Card(Rank.ACE, Suit.DIAMOND),
            )
        val player = Player(GamblerInfo("Player"))
        player.addCard(cards)
        assertEquals(22, player.score)
    }

    @Test
    fun `should asser that - get cards properly`() {
        val deck = Deck()
        val player = Player(GamblerInfo("Jin"))
        player.addCard(deck.drawCard(4))
        assertThat(player.cards.size).isEqualTo(4)
    }

    @ParameterizedTest
    @MethodSource("provideCardCombinations")
    fun `should asser that - blackjack and bust detection works correctly`(
        cards: List<Card>,
        expectedBlackjack: Boolean,
        expectedBust: Boolean,
    ) {
        val player = Player(GamblerInfo("TestPlayer"))
        player.addCard(cards)

        assertThat(player.isBlackJack()).isEqualTo(expectedBlackjack)
        assertThat(player.isBusted()).isEqualTo(expectedBust)
    }

    companion object {
        @JvmStatic
        fun provideCardCombinations(): List<Arguments> {
            return listOf(
                // Blackjack: Ace + 10-value card (exactly 2 cards, total 21)
                Arguments.of(
                    listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.HEART)),
                    true,
                    false,
                ),
                // Blackjack: Ace + Queen
                Arguments.of(
                    listOf(Card(Rank.ACE, Suit.HEART), Card(Rank.QUEEN, Suit.SPADE)),
                    true,
                    false,
                ),
                // Not blackjack: 21 with 3 cards
                Arguments.of(
                    listOf(Card(Rank.SEVEN, Suit.SPADE), Card(Rank.SEVEN, Suit.HEART), Card(Rank.SEVEN, Suit.CLUB)),
                    false,
                    false,
                ),
                // Bust: over 21
                Arguments.of(
                    listOf(Card(Rank.KING, Suit.SPADE), Card(Rank.QUEEN, Suit.HEART), Card(Rank.FIVE, Suit.DIAMOND)),
                    false,
                    true,
                ),
                // Normal hand: under 21, not blackjack
                Arguments.of(
                    listOf(Card(Rank.NINE, Suit.SPADE), Card(Rank.EIGHT, Suit.HEART)),
                    false,
                    false,
                ),
            )
        }
    }
}
