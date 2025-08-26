package blackjack.state

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
        hand = Hand(mutableListOf(Card(CardSuit.DIAMOND, 10), Card(CardSuit.HEART, 10), Card(CardSuit.CLUB, 5)))
        deck = Deck(mutableListOf(Card(CardSuit.SPADE, 2)))
    }

    @Test
    fun `Busted is a Finished state`() {
        val busted = Busted(hand, deck)
        assertTrue(busted is Finished)
    }
}
