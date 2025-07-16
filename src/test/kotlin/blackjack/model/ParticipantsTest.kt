package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `should deal 2 cards to each participant`() {
        val deck = Deck()
        val players = Players(listOf(Player("P1", 100000), Player("P2", 200000)))
        val dealer = Dealer()
        val participants = Participants(players, dealer)

        participants.dealInitialCards(deck)

        assertEquals(2, dealer.cardsInHand.size)
        participants.all().forEach {
            assertEquals(2, it.cardsInHand.size)
        }
    }

    @Test
    fun `should evaluate winningMoney after game`() {
        val deck = Deck()
        val players = Players(listOf(Player("P1", 100000), Player("P2", 200000)))
        val dealer = Dealer()
        val participants = Participants(players, dealer)
        participants.dealInitialCards(deck)
        participants.dealerTurn(deck)
        participants.evaluateResults()
        assertThat(participants.getPlayers().all { it.returnWinningMoneyForPlayer() in -200000..300000 })
    }
}
