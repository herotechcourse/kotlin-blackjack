package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.Deck
import blackjack.model.participant.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var deck: Deck
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        // Create a predictable deck
        deck =
            Deck(
                mutableListOf(
                    Card(Card.Suit.SPADES, Card.Rank.TWO),
                    Card(Card.Suit.HEARTS, Card.Rank.FIVE),
                    Card(Card.Suit.CLUBS, Card.Rank.KING),
                ),
            )
        dealer = Dealer(deck = deck)
    }

    @Test
    fun `should deal card from bottom of deck`() {
        val card = dealer.dealCard()
        println("Dealt card: $card")
        assertThat(card).isEqualTo(Card(Card.Suit.CLUBS, Card.Rank.KING))
    }

    @Test
    fun `should shuffle the deck`() {
        val dealer2 = Dealer()
        val originalOrder = dealer2.deck.cards
        dealer2.shuffleDeck()
        assertThat(dealer2.deck.cards).isNotEqualTo(originalOrder) // flaky if already shuffled
    }

    @Test
    fun `should stand when score exceeds 16`() {
        dealer.addCard(Card(Card.Suit.HEARTS, Card.Rank.TEN))
        dealer.addCard(Card(Card.Suit.CLUBS, Card.Rank.EIGHT))
        assertThat(dealer.shouldNotStand()).isFalse()
    }

    @Test
    fun `should not stand when score is 16 or below`() {
        dealer.addCard(Card(Card.Suit.HEARTS, Card.Rank.EIGHT))
        dealer.addCard(Card(Card.Suit.CLUBS, Card.Rank.EIGHT)) // score 16
        assertThat(dealer.shouldNotStand()).isTrue()
    }

    @Test
    fun `should hand return a list of the first class if is only the first round`() {
        val card = Card(Card.Suit.DIAMONDS, Card.Rank.TWO)
        dealer.addCard(card)
        dealer.addCard(Card(Card.Suit.SPADES, Card.Rank.THREE))
        val result = dealer.hand
        assertThat(result).isEqualTo(listOf(card))
    }

    @Test
    fun `should return full hand if showAllCards is true`() {
        val card1 = Card(Card.Suit.DIAMONDS, Card.Rank.TWO)
        val card2 = Card(Card.Suit.SPADES, Card.Rank.THREE)
        dealer.addCard(card1)
        dealer.addCard(card2)
        dealer.showAllCards()
        val result = dealer.hand
        assertThat(result).isEqualTo(listOf(card1, card2))
    }

    @Test
    fun `toString should show the name`() {
        val result = dealer.toString()
        assertThat(result).isEqualTo("Dealer")
    }
}
