package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackjackGameTest {
    @Test
    fun `after setUp table any player and dealer has 2 cards`() {
        val cardDeck = CardDeck()
        val players = ParticipantsFactory.generatePlayers(listOf("Player 1", "Player 2"))
        val dealer = ParticipantsFactory.generateDealer()

        BlackjackGame.setUpTable(dealer, players, cardDeck)

        players.forEach { player ->
            assertThat(player.state.hand.size).isEqualTo(2)
        }

        assertThat(dealer.state.hand.size).isEqualTo(2)
    }
}
