package blackjack.model

import blackjack.Fixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `Dealer init with hand of two cards`() {
        val dealer = Dealer()

        assertEquals(Hand.INIT_CARDS_SIZE, dealer.hand.cards.size)
    }

    @Test
    fun `Dealer has hand which is a list of card`() {
        val deck = PlayingCard.deck

        // init new dealer with first card
        val firstCard = deck.giveCard()
        val hand = Hand(listOf(firstCard))
        val dealer = Dealer(hand = hand)
        assertEquals(firstCard, dealer.hand.cards.last())

        // draw second card
        val secondCard = deck.giveCard()
        dealer.drawCard(secondCard)
        assertEquals(secondCard, dealer.hand.cards.last())
    }

    @Test
    fun `Dealer can init with hand having a card`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_SEVEN))
        val dealer = Dealer(hand = hand)

        assertEquals(1, dealer.hand.cards.size)
    }

    @Test
    fun `requestCard() - request true`() {
        val dealer = Dealer()
        val request = dealer.requestCard { true }
        assertEquals(true, request)
    }

    @Test
    fun `requestCard() - request false`() {
        val dealer = Dealer()
        val request = dealer.requestCard { false }
        assertEquals(false, request)
    }

    @Test
    fun `drawCard()`() {
        val hand = Hand(listOf(Fixture.SPADES_ACE))
        val dealer = Dealer(hand = hand)

        // draw first card
        assertEquals(1, dealer.hand.cards.size)
        assertEquals(Fixture.SPADES_ACE, dealer.hand.cards.last())

        // draw second card
        dealer.drawCard(Fixture.DIAMONDS_JACK)
        assertEquals(2, dealer.hand.cards.size)
        assertEquals(Fixture.DIAMONDS_JACK, dealer.hand.cards.last())
    }

    @Test
    fun `calculateHand() - dealer has TWO and TEN`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_TWO, Fixture.DIAMONDS_TEN))
        val dealer = Dealer(hand = hand)

        assertEquals(12, dealer.hand.calculateHand())
    }

    @Test
    fun `calculateHand() - dealer has Two ACE`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_ACE, Fixture.SPADES_ACE))
        val dealer = Dealer(hand = hand)

        assertEquals(12, dealer.hand.calculateHand())
    }

    @Test
    fun `calculateHand() - dealer has Three ACE and TWO`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_ACE, Fixture.SPADES_ACE, Fixture.DIAMONDS_TWO, Fixture.HEARTS_ACE))
        val dealer = Dealer(hand = hand)

        assertEquals(15, dealer.hand.calculateHand())
    }

    @Test
    fun `calculateHand() - dealer has Three ACE and TEN`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_ACE, Fixture.SPADES_ACE, Fixture.DIAMONDS_TEN, Fixture.HEARTS_ACE))
        val dealer = Dealer(hand = hand)

        assertEquals(13, dealer.hand.calculateHand())
    }

    @Test
    fun `isBust() - dealer is busted`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_JACK, Fixture.DIAMONDS_QUEEN))
        val dealer = Dealer(hand = hand)

        assertEquals(true, dealer.hand.isBust)
    }

    @Test
    fun `isBust() - dealer is not busted`() {
        val hand = Hand(listOf(Fixture.DIAMONDS_TEN, Fixture.DIAMONDS_JACK))
        val dealer = Dealer(hand = hand)

        assertEquals(false, dealer.hand.isBust)
    }

    @Test
    fun `shouldDrawCardOrNot() - return true if dealer's hand is less or equal to 16`() {
        val handOfDealer = Hand(listOf(Fixture.DIAMONDS_SIX, Fixture.DIAMONDS_TEN))
        val dealer = Dealer(hand = handOfDealer)

        assertEquals(true, dealer.shouldDrawCardOrNot())
    }

    @Test
    fun `shouldDrawCardOrNot() - return false if dealer's hand is bigger or equal to 17`() {
        val handOfDealer = Hand(listOf(Fixture.DIAMONDS_SEVEN, Fixture.DIAMONDS_TEN))
        val dealer = Dealer(hand = handOfDealer)

        assertEquals(false, dealer.shouldDrawCardOrNot())
    }
}
