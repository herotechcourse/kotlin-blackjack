package blackjack

import blackjack.model.cards.HandCards
import blackjack.model.states.BlackJack
import blackjack.model.states.Hit
import blackjack.model.states.Init
import blackjack.model.states.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InitTest {
    @Test
    fun `test empty hand`() {
        val state: State = Init()
        assertThat(state.hand.cards.size).isEqualTo(0)
        assertThat(state).isInstanceOf(Init::class.java)
    }

    @Test
    fun `test init Init with 2 cards`() {
        assertThrows<IllegalStateException> { Init(HandCards.from(QUEEN_DIAMONDS, QUEEN_HEARTS)) }
    }

    @Test
    fun `test hand of 3 cards throws IllegalStateException`() {
        val handCards = HandCards(listOf(THREE_CLUBS, THREE_HEARTS, THREE_SPADES))
        assertThrows<IllegalStateException> { Init(handCards) }
    }

    @Test
    fun `test drawing one card`() {
        var state: State = Init()
        state = state.draw(ACE_DIAMONDS)
        assertThat(state.hand.cards.size).isEqualTo(1)
        assertThat(state).isInstanceOf(Init::class.java)
    }

    @Test
    fun `test drawing two cards changes state`() {
        var state: State = Init()
        repeat(2) { state = state.draw(SIX_DIAMONDS) }
        assertThat(state.hand.cards.size).isEqualTo(2)
        assertThat(state).isNotInstanceOf(Init::class.java)
    }

    @Test
    fun `test drawing two cards changes state to Hit`() {
        var state: State = Init()
        repeat(2) { state = state.draw(SIX_DIAMONDS) }
        assertThat(state.hand.cards.size).isEqualTo(2)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `test drawing two cards changes state to Blackjack`() {
        var state: State = Init()
        state = state.draw(QUEEN_DIAMONDS)
        state = state.draw(ACE_CLUBS)
        assertThat(state.hand.cards.size).isEqualTo(2)
        assertThat(state).isInstanceOf(BlackJack::class.java)
    }
}
