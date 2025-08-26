package blackjack.state

import blackjack.enum.CardSuit
import blackjack.model.Card
import blackjack.model.Deck
import blackjack.model.Hand
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StayTest {
    private lateinit var hand: Hand
    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        hand = Hand(mutableListOf(Card(CardSuit.HEART, 7)))
        deck = Deck(mutableListOf(Card(CardSuit.CLUB, 3)))
    }

    @Test
    fun `Stay is a Finished state`() {
        val stay = Stay(hand, deck)
        assertTrue(stay is Finished)
    }
}
