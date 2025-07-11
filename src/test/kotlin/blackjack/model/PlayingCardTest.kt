package blackjack.model

import blackjack.Fixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlayingCardTest {
    @Test
    fun `PlayingCard create valid card`() {
        val rank = Rank.ACE
        val suit = Suit.SPADES
        val card = PlayingCard.of(rank, suit)
        assertEquals(Fixture.SPADES_ACE, card)
    }

    @Test
    fun `PlayingCard create valid card 2`() {
        val rank = Rank.JACK
        val suit = Suit.DIAMONDS
        val card = PlayingCard.of(rank, suit)
        assertEquals(Fixture.DIAMONDS_JACK, card)
    }
}
