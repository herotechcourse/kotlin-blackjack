package blackjack.model

import blackjack.controller.GameManager
import blackjack.model.participant.PlayerFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun `init game manager, the manage should set up a game`() {
        val listOfName = listOf("Vito", "Mina")
        val players = PlayerFactory.createPlayers(listOfName)
        val dealer = PlayerFactory.createDealer()

        val gameManager = GameManager(dealer, players)
        gameManager.setUp()

        assertThat(dealer.numberInHand()).isEqualTo(1)
        assertThat(players[0].numberInHand()).isEqualTo(2)
        assertThat(players[1].numberInHand()).isEqualTo(2)
    }

    @Test
    fun `test single player`() {
        val listOfName = listOf("Vito", "Mina")
        val players = PlayerFactory.createPlayers(listOfName)
        val dealer = PlayerFactory.createDealer()

        val gameManager = GameManager(dealer, players)
        gameManager.setUp()

        val sizeOfDealerCard = dealer.numberInHand()
        gameManager.round(dealer)

        assertThat(sizeOfDealerCard).isNotEqualTo(dealer.numberInHand())
    }
}
