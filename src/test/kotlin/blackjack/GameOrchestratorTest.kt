package blackjack

import blackjack.model.Deck
import blackjack.model.Player
import blackjack.service.GameOrchestrator
import blackjack.service.InputProcessor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameOrchestratorTest {
    val gameOrchestrator = GameOrchestrator(Deck(), InputProcessor())

    @Test
    fun `dealer and players received right amount of cards`() {
        val dealer = Player("dealer")
        val players =
            listOf(
                Player("p1"),
                Player("p2"),
                Player("p3"),
            )
        gameOrchestrator.dealInitialCards(dealer, players)
        assertThat(dealer.cards.size).isEqualTo(2)
        for (player in players) {
            assertThat(player.cards.size).isEqualTo(2)
        }
    }
}
