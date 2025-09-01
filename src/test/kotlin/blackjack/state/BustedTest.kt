package blackjack.state

import blackjack.enum.CardNumber
import blackjack.enum.CardSuit
import blackjack.model.Card
import blackjack.model.Deck
import blackjack.model.Hand
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BustedTest {
    private lateinit var hand: Hand
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        hand =
            Hand(
                mutableListOf(
                    Card(CardSuit.DIAMOND, CardNumber.TEN),
                    Card(CardSuit.HEART, CardNumber.TEN),
                    Card(CardSuit.CLUB, CardNumber.FIVE),
                ),
            )
        deck = Deck(mutableListOf(Card(CardSuit.SPADE, CardNumber.TWO)))
    }

    @Test
    fun `Busted is a Finished state`() {
        val busted = Busted(hand, deck)
        assertTrue(busted is Finished)
    }

    @Test
    fun `run, stay, and finish all return same instance`() {
        val finished = Blackjack(hand, deck)
        assertTrue(finished.run() === finished)
        assertTrue(finished.stay() === finished)
    }
}
