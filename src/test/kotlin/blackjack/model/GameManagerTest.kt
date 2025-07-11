package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameManagerTest {
    @Test
    fun `init game manager, the manage should set up a game`() {
        val listOfName = listOf("Vito", "Mina")
        val players = PlayerFactory.with(listOfName)
        val dealer = PlayerFactory.createDealer()
        GameManager(dealer, players)

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
        val sizeOfDealerCard = dealer.numberInHand()

        gameManager.round(dealer, { true }, { true })

        assertThat(sizeOfDealerCard).isNotEqualTo(dealer.numberInHand())
    }
}
