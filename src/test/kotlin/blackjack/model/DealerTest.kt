package blackjack.model

import blackjack.enum.CardNumber
import blackjack.enum.CardSuit
import blackjack.state.Running
import blackjack.state.Started
import blackjack.state.State
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var deck: Deck
    private lateinit var dealer: Dealer
    private lateinit var state: State

    @BeforeEach
    fun setUp() {
        deck =
            Deck(
                mutableListOf(
                    Card(CardSuit.HEART, CardNumber.TWO),
                    Card(CardSuit.SPADE, CardNumber.TEN),
                    Card(CardSuit.CLUB, CardNumber.FIVE),
                ),
            )
        dealer = Dealer(deck)
    }

    @Test
    fun `playTurn with Started state deals two cards and transitions to Running or Blackjack`() {
        dealer.state = Started(Hand(), deck)
        dealer.playTurn()
        val handSize = dealer.state.hand.cards.size
        assertTrue(handSize == 2, "Dealer should have two cards after first playTurn")
        assertTrue(dealer.state is Running || dealer.state is blackjack.state.Blackjack)
    }

    @Test
    fun `playTurn with Running state and sum is less than 17 draws a card`() {
        val hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.TWO), Card(CardSuit.CLUB, CardNumber.THREE)))
        dealer.state = Running(hand, deck)
        dealer.playTurn()
        assertEquals(3, dealer.state.hand.cards.size)
    }

    @Test
    fun `playTurn with Running state and sum is greater than or equal to 17 does not draw a card`() {
        val hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.SEVEN)))
        dealer.state = Running(hand, deck)
        dealer.playTurn()
        assertEquals(2, dealer.state.hand.cards.size)
    }

    @Test
    fun `shouldDraw returns true if Running and sum is less than 17`() {
        val hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.TWO), Card(CardSuit.CLUB, CardNumber.THREE)))
        dealer.state = Running(hand, deck)
        assertTrue(dealer.state.shouldDraw(17))
    }

    @Test
    fun `shouldDraw returns false if Running and sum is greater than or equal to 17`() {
        val hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.CLUB, CardNumber.SEVEN)))
        dealer.state = Running(hand, deck)
        assertFalse(dealer.state.shouldDraw(17))
    }

    @Test
    fun `shouldDraw returns false if not Running`() {
        dealer.state = Started(Hand(), deck)
        assertFalse(dealer.state.shouldDraw(17))
    }
}
