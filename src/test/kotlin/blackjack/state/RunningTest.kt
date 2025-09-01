package blackjack.state

import blackjack.enum.CardNumber
import blackjack.enum.CardSuit
import blackjack.model.Card
import blackjack.model.Deck
import blackjack.model.Hand
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RunningTest {
    private lateinit var hand: Hand
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.FIVE), Card(CardSuit.SPADE, CardNumber.TEN)))
        deck = Deck(mutableListOf(Card(CardSuit.CLUB, CardNumber.SIX)))
    }

    @Test
    fun `run adds a card and returns Busted if sum is 21`() {
        hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TEN)))
        deck = Deck(mutableListOf(Card(CardSuit.CLUB, CardNumber.FIVE)))
        val running = Running(hand, deck)
        val result = running.run()
        assertTrue(result is Busted)
    }

    @Test
    fun `run adds a card and returns Finished if sum is equal to 21`() {
        hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.FIVE)))
        deck = Deck(mutableListOf(Card(CardSuit.CLUB, CardNumber.SIX)))
        val running = Running(hand, deck)
        val result = running.run()
        assertTrue(result is Finished)
    }

    @Test
    fun `run adds a card and returns Running if sum is less than 21`() {
        hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.FIVE), Card(CardSuit.SPADE, CardNumber.TEN)))
        deck = Deck(mutableListOf(Card(CardSuit.CLUB, CardNumber.TWO)))
        val running = Running(hand, deck)
        val result = running.run()
        assertTrue(result is Running)
    }
}
