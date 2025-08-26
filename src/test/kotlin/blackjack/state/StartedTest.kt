package blackjack.state

import blackjack.enum.CardSuit
import blackjack.model.Card
import blackjack.model.Deck
import blackjack.model.Hand
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StartedTest {
    private lateinit var hand: Hand
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        hand = Hand()
        deck = Deck(mutableListOf(Card(CardSuit.HEART, 10), Card(CardSuit.SPADE, 1)))
    }

    @Test
    fun `run returns Blackjack if hand is blackjack`() {
        hand = Hand()
        val started = Started(hand, deck)
        val result = started.run()
        assertTrue(result is Blackjack)
    }

    @Test
    fun `run returns Running if hand is not blackjack`() {
        hand = Hand(mutableListOf(Card(CardSuit.HEART, 2), Card(CardSuit.SPADE, 10)))
        val started = Started(hand, deck)
        val result = started.run()
        assertTrue(result is Running)
    }

    @Test
    fun `stay returns Stay`() {
        val started = Started(hand, deck)
        assertTrue(started.stay() is Stay)
    }

    @Test
    fun `finish returns Finished`() {
        val started = Started(hand, deck)
        assertTrue(started.finish() is Finished)
    }
}
