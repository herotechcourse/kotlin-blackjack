package blackjack.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `should deal 2 cards to each participant`() {
        val deck = Deck()
        val players = Players(listOf(Player("P1"), Player("P2")))
        val dealer = Dealer()
        val participants = Participants(players, dealer)

        participants.dealInitialCards(deck)

        assertEquals(2, dealer.cardsInHand.size)
        participants.all().forEach {
            assertEquals(2, it.cardsInHand.size)
        }
    }
}
