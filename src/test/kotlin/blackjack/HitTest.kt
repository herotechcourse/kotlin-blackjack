package blackjack

import blackjack.model.cards.HandCards
import blackjack.model.states.BlackJack
import blackjack.model.states.Bust
import blackjack.model.states.Hit
import blackjack.model.states.State
import blackjack.model.states.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HitTest {
    @Test
    fun `test hit to hit`() {
        var state: State = Hit(HandCards.from(QUEEN_CLUBS, THREE_CLUBS))
        state = state.draw(TWO_CLUBS)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `test hit to busted`() {
        var state: State = Hit(HandCards.from(QUEEN_CLUBS, KING_CLUBS))
        state = state.draw(JACK_CLUBS)
        assertThat(state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `test hit to hit with 21`() {
        var state: State = Hit(HandCards.from(QUEEN_CLUBS, KING_CLUBS))
        state = state.draw(ACE_HEARTS)
        assertThat(state).isInstanceOf(Hit::class.java)
        assertThat(state).isNotInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `test hit to stay`() {
        var state: State = Hit(HandCards.from(TWO_CLUBS, THREE_HEARTS))
        state = state.stay()
        assertThat(state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `test throws exception if amount of cards is less than 2`() {
        assertThrows<IllegalStateException> { Hit(HandCards()) }
        assertThrows<IllegalStateException> { Hit(HandCards.from(QUEEN_CLUBS, QUEEN_DIAMONDS, QUEEN_HEARTS)) }
    }

    @Test
    fun `test throws exception if total is more than 21`() {
        assertThrows<IllegalStateException> { Hit(HandCards.from(QUEEN_CLUBS, QUEEN_DIAMONDS, QUEEN_HEARTS)) }
    }
}
