package blackjack.state

import blackjack.enum.CardNumber
import blackjack.enum.CardSuit
import blackjack.model.Card
import blackjack.model.Deck
import blackjack.model.Hand
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FinishedTest {
    private lateinit var hand: Hand
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        hand = Hand(mutableListOf(Card(CardSuit.HEART, CardNumber.FIVE)))
        deck = Deck(mutableListOf(Card(CardSuit.SPADE, CardNumber.TWO)))
    }

    @Test
    fun `run, stay, and finish all return same instance`() {
        val finished = Finished(hand, deck)
        assertTrue(finished.run() === finished)
        assertTrue(finished.stay() === finished)
        assertTrue(finished.finish() === finished)
    }
}
