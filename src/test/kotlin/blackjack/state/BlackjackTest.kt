package blackjack.state

import blackjack.enum.CardNumber
import blackjack.enum.CardSuit
import blackjack.model.Card
import blackjack.model.Deck
import blackjack.model.Hand
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BlackjackTest {
    private lateinit var hand: Hand
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.ACE), Card(CardSuit.SPADE, CardNumber.TEN)))
        deck = Deck(mutableListOf(Card(CardSuit.CLUB, CardNumber.TWO)))
    }

    @Test
    fun `Blackjack is a Finished state`() {
        val blackjack = Blackjack(hand, deck)
        assertTrue(blackjack is Finished)
    }

    @Test
    fun `run, stay, and finish all return same instance`() {
        val finished = Blackjack(hand, deck)
        assertTrue(finished.run() === finished)
        assertTrue(finished.stay() === finished)
    }
}
