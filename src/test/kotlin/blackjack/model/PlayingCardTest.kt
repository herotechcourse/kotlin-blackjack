package blackjack.model

import blackjack.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayingCardTest {
    @BeforeEach
    fun init() {
        PlayingCard.deck.cards = PlayingCard.generateAllDeck()
    }

    @Test
    fun `PlayingCard create valid card`() {
        val rank = Rank.ACE
        val suit = Suit.SPADES
        val card = PlayingCard.of(rank, suit)
        assertThat(card.suit).isEqualTo(Fixture.SPADES_ACE.suit)
        assertThat(card.rank).isEqualTo(Fixture.SPADES_ACE.rank)
    }

    @Test
    fun `PlayingCard create valid card 2`() {
        val rank = Rank.JACK
        val suit = Suit.DIAMONDS
        val card = PlayingCard.of(rank, suit)
        assertThat(card.suit).isEqualTo(Fixture.DIAMONDS_JACK.suit)
        assertThat(card.rank).isEqualTo(Fixture.DIAMONDS_JACK.rank)
    }
}
