package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun `init game manager, the manage should set up a game`() {
        val listOfName = listOf("Vito", "Mina")
        val players = PlayerFactory.with(listOfName)
        val dealer = PlayerFactory.createDealer()

        val gameManager = GameManager(dealer, players)
        assertThat(dealer.numberInHand()).isEqualTo(1)
        assertThat(players[0].numberInHand()).isEqualTo(2)
        assertThat(players[1].numberInHand()).isEqualTo(2)
    }

    @Test
    fun `test single player`() {
        val listOfName = listOf("Vito", "Mina")
        val players = PlayerFactory.with(listOfName)
        val dealer = PlayerFactory.createDealer()

        val gameManager = GameManager(dealer, players)
        gameManager.setUp()

        val sizeOfDealerCard = dealer.numberInHand()
        val playerResult = gameManager.singlePlayerResult(dealer) { true }

        assertThat(playerResult.cards.size).isNotEqualTo(sizeOfDealerCard)
    }
}
