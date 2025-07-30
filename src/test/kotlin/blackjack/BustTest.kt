package blackjack

import blackjack.model.cards.HandCards
import blackjack.model.states.Bust
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class BustTest {
    @ParameterizedTest
    @MethodSource("invalidBustHands")
    fun `test init bust with wrong cards`(hand: HandCards) {
        assertThrows<IllegalStateException> { Bust(hand) }
    }

    @Test
    fun `test init bust with correct cards`() {
        val handCards = HandCards.from(ACE_DIAMONDS, QUEEN_DIAMONDS, QUEEN_SPADES, THREE_HEARTS)
        assertDoesNotThrow { Bust(handCards) }
    }

    @Test
    fun `test draw throws exception`() {
        val bust = Bust(VALID_BUST_HAND)
        assertThrows<IllegalStateException> { bust.draw(ACE_DIAMONDS) }
    }

    @Test
    fun `test stay throws exception`() {
        val bust = Bust(VALID_BUST_HAND)
        assertThrows<IllegalStateException> { bust.stay() }
    }

    companion object {
        @JvmStatic
        fun invalidBustHands(): List<Arguments> =
            listOf(
                Arguments.of(HandCards.from(ACE_DIAMONDS, QUEEN_DIAMONDS)),
                Arguments.of(HandCards.from(THREE_HEARTS, FIVE_HEARTS)),
                Arguments.of(HandCards()),
            )

        private val VALID_BUST_HAND = HandCards.from(QUEEN_DIAMONDS, KING_CLUBS, JACK_CLUBS)
    }
}
