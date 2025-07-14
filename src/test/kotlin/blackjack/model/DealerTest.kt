package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
    }

    @Test
    fun `should initialize a Dealer with a default name Dealer`() {
        assertThat(Dealer("Dealer")).isInstanceOf(Dealer::class.java)
    }

    @Test
    fun `should deal a card from the deck`() {
        val card = dealer.dealCard()
        assertThat(card).isInstanceOf(Card::class.java)
    }

    @Test
    fun `should return false when dealer has more than 16`() {
        dealer.addCard(Card(Card.Suit.HEARTS, Card.Rank.NINE))
        dealer.addCard(Card(Card.Suit.SPADES, Card.Rank.NINE))

        assertThat(dealer.shouldNotStand()).isFalse()
    }

    @Test
    fun `should return true when dealer has 16 or less`() {
        dealer.addCard(Card(Card.Suit.HEARTS, Card.Rank.EIGHT))
        dealer.addCard(Card(Card.Suit.SPADES, Card.Rank.EIGHT))

        assertThat(dealer.shouldNotStand()).isTrue()
    }

    @Test
    fun `should reveal all cards when showAllCards is called`() {
        assertThat(dealer.isShowingAllCards()).isFalse()

        dealer.showAllCards()

        assertThat(dealer.isShowingAllCards()).isTrue()
    }

    @Test
    fun `should return dealt cards`() {
        val card = Card(Card.Suit.CLUBS, Card.Rank.SIX)
        dealer.addCard(card)

        val dealt = dealer.getDealtCards()

        assertThat(dealt).containsExactly(card)
    }

    @Test
    fun `should handle setResultFor when player busts`() {
        val player =
            Player("bo").apply {
                addCard(Card(Card.Suit.SPADES, Card.Rank.KING))
                addCard(Card(Card.Suit.HEARTS, Card.Rank.QUEEN))
                addCard(Card(Card.Suit.CLUBS, Card.Rank.TWO))
            }

        dealer.setResultFor(player)

        assertThat(player.gameResults.loses).isEqualTo(1)
        assertThat(dealer.gameResults.wins).isEqualTo(1)
    }

    @Test
    fun `should handle setResultFor when dealer busts`() {
        val player =
            Player("bo").apply {
                addCard(Card(Card.Suit.DIAMONDS, Card.Rank.EIGHT))
                addCard(Card(Card.Suit.CLUBS, Card.Rank.EIGHT))
            }
        dealer.apply {
            addCard(Card(Card.Suit.HEARTS, Card.Rank.KING))
            addCard(Card(Card.Suit.SPADES, Card.Rank.QUEEN))
            addCard(Card(Card.Suit.DIAMONDS, Card.Rank.TWO))
        }

        dealer.setResultFor(player)

        assertThat(player.gameResults.wins).isEqualTo(1)
        assertThat(dealer.gameResults.loses).isEqualTo(1)
    }

    @Test
    fun `should handle setResultFor when it is a tie`() {
        val player =
            Player("bo").apply {
                addCard(Card(Card.Suit.CLUBS, Card.Rank.NINE))
                addCard(Card(Card.Suit.HEARTS, Card.Rank.NINE))
            }
        dealer.apply {
            addCard(Card(Card.Suit.SPADES, Card.Rank.NINE))
            addCard(Card(Card.Suit.DIAMONDS, Card.Rank.NINE))
        }

        dealer.setResultFor(player)

        assertThat(player.gameResults.ties).isEqualTo(1)
        assertThat(dealer.gameResults.ties).isEqualTo(1)
    }

    @Test
    fun `should handle setResultFor when player has blackjack and dealer doesn't`() {
        val player =
            Player("bo").apply {
                addCard(Card(Card.Suit.CLUBS, Card.Rank.ACE))
                addCard(Card(Card.Suit.HEARTS, Card.Rank.KING))
            }
        dealer.addCard(Card(Card.Suit.SPADES, Card.Rank.EIGHT))
        dealer.addCard(Card(Card.Suit.DIAMONDS, Card.Rank.NINE))

        dealer.setResultFor(player)

        assertThat(player.gameResults.wins).isEqualTo(1)
        assertThat(dealer.gameResults.loses).isEqualTo(1)
    }
}
