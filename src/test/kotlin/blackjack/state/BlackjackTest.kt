package blackjack.state

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
        hand = Hand(mutableListOf(Card(CardSuit.HEART, 1), Card(CardSuit.SPADE, 10)))
        deck = Deck(mutableListOf(Card(CardSuit.CLUB, 2)))
    }

    @Test
    fun `Blackjack is a Finished state`() {
        val blackjack = Blackjack(hand, deck)
        assertTrue(blackjack is Finished)
    }
}
