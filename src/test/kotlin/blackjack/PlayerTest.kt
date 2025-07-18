package blackjack

import blackjack.model.Card
import blackjack.model.Deck
import blackjack.model.Player
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {
    @Test
    fun `should calculate correct score for single card`() {
        val card = Card(Rank.TEN, Suit.SPADE)
        val player = Player("Player")
        player.addCard(listOf(card))
        assertEquals(10, player.score)
    }

    @Test
    fun `should treat Ace as 1 when two Aces would exceed 21`() {
        val cards = listOf(Card(Rank.ACE, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val player = Player("Player")
        player.addCard(cards)
        assertEquals(12, player.score)
    }

    @Test
    fun `should treat Ace as 11 when total stays under 21`() {
        val cards = listOf(Card(Rank.FOUR, Suit.SPADE), Card(Rank.ACE, Suit.HEART))
        val player = Player("Player")
        player.addCard(cards)
        assertEquals(15, player.score)
    }

    @Test
    fun `should convert Ace from 11 to 1 to avoid bust`() {
        val cards =
            listOf(
                Card(Rank.TWO, Suit.SPADE),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.KING, Suit.HEART),
            )
        val player = Player("Player")
        player.addCard(cards)
        assertEquals(13, player.score)
    }

    @Test
    fun `should convert both Aces to 1 when needed to avoid bust`() {
        val cards =
            listOf(
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.KING, Suit.HEART),
            )
        val player = Player("Player")
        player.addCard(cards)
        assertEquals(12, player.score)
    }

    @Test
    fun `should convert all Aces to 1 but still bust with multiple Aces`() {
        val cards =
            listOf(
                Card(Rank.NINE, Suit.SPADE),
                Card(Rank.NINE, Suit.HEART),
                Card(Rank.ACE, Suit.HEART),
                Card(Rank.ACE, Suit.SPADE),
                Card(Rank.ACE, Suit.CLUB),
                Card(Rank.ACE, Suit.DIAMOND),
            )
        val player = Player("Player")
        player.addCard(cards)
        assertEquals(22, player.score)
    }

    @Test
    fun `should track card count correctly when adding multiple cards`() {
        val deck = Deck()
        val player = Player("Jin")
        player.addCard(deck.drawCard(4))
        assertThat(player.cards.size).isEqualTo(4)
    }

    @ParameterizedTest
    @MethodSource("provideCardCombinations")
    fun `should correctly detect blackjack and bust conditions`(
        cards: List<Card>,
        expectedBlackjack: Boolean,
        expectedBust: Boolean,
    ) {
        val player = Player("TestPlayer")
        player.addCard(cards)

        player.updateStatus()
        assertThat(player.status.isBlackjack).isEqualTo(expectedBlackjack)
        assertThat(player.status.isBusted).isEqualTo(expectedBust)
    }

    @Test
    fun `should validate bet amount correctly`() {
        val player = Player("TestPlayer")

        assertThrows<IllegalArgumentException> { player.updateBetAmount(-1) }
        assertThrows<IllegalArgumentException> { player.updateBetAmount(0) }
    }

    @Test
    fun `should update variable 'betAmount' right away`() {
        val player = Player("TestPlayer")
        player.updateBetAmount(100)
        assertThat(player.betAmount).isEqualTo(100)

        player.updateBetAmount(500)
        assertThat(player.betAmount).isEqualTo(500)
    }

    @Test
    fun `should initialize with zero bet amount`() {
        val player = Player("TestPlayer")
        assertThat(player.betAmount).isEqualTo(0)
    }

    @Test
    fun `should correctly identify player name`() {
        val playerName = "Alice"
        val player = Player(playerName)
        assertThat(player.name).isEqualTo(playerName)
    }

    @Test
    fun `should initialize with empty cards and zero score`() {
        val player = Player("TestPlayer")
        assertThat(player.cards).isEmpty()
        assertThat(player.score).isEqualTo(0)
        assertThat(player.status.isBlackjack).isFalse()
        assertThat(player.status.isBusted).isFalse()
    }

    companion object {
        @JvmStatic
        fun provideCardCombinations(): List<Arguments> {
            return listOf(
                // Blackjack: Ace + King
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
                    listOf(
                        Card(Rank.SEVEN, Suit.SPADE),
                        Card(Rank.SEVEN, Suit.HEART),
                        Card(Rank.SEVEN, Suit.CLUB)
                    ),
                    false,
                    false,
                ),
                // Bust: over 21
                Arguments.of(
                    listOf(
                        Card(Rank.KING, Suit.SPADE),
                        Card(Rank.QUEEN, Suit.HEART),
                        Card(Rank.FIVE, Suit.DIAMOND)
                    ),
                    false,
                    true,
                ),
                // Normal hand: under 21
                Arguments.of(
                    listOf(Card(Rank.NINE, Suit.SPADE), Card(Rank.EIGHT, Suit.HEART)),
                    false,
                    false,
                ),
            )
        }
    }
}
