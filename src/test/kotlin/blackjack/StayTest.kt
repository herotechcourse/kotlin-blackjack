package blackjack

import blackjack.model.cards.HandCards
import blackjack.model.states.Stay
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StayTest {
    @ParameterizedTest
    @MethodSource("invalidStayHands")
    fun `test init Stay with wrong cards`(hand: HandCards) {
        assertThrows<IllegalStateException> { Stay(hand) }
    }

    @Test
    fun `test init stay with correct cards`() {
        val handCards = HandCards.from(QUEEN_DIAMONDS, QUEEN_SPADES)
        assertDoesNotThrow { Stay(handCards) }
    }

    @Test
    fun `test draw throws exception`() {
        val bust = Stay(VALID_STAY_HAND)
        assertThrows<IllegalStateException> { bust.draw(ACE_DIAMONDS) }
    }

    @Test
    fun `test stay throws exception`() {
        val bust = Stay(VALID_STAY_HAND)
        assertThrows<IllegalStateException> { bust.stay() }
    }

    companion object {
        @JvmStatic
        fun invalidStayHands(): List<Arguments> =
            listOf(
                Arguments.of(HandCards.from(ACE_DIAMONDS)),
                Arguments.of(HandCards()),
            )

        private val VALID_STAY_HAND = HandCards.from(ACE_DIAMONDS, FIVE_HEARTS)
    }
}
