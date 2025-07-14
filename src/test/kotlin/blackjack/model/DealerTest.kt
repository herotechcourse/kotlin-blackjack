package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `should able to draw a card if the total value of cards is equal or less than 16`() {
        val totalValueOfCards = 14
        val dealer = Dealer()
        assertThat(dealer.mustDraw(totalValueOfCards)).isTrue
    }

    @Test
    fun `should increase from 0 to 2 cards in hand when giving first hand`() {
        val dealer = Dealer()
        assertThat(dealer.cardsInHand.cards.size).isEqualTo(0)
        dealer.selfDrawInitialCards()
        assertThat(dealer.cardsInHand.cards.size).isEqualTo(2)
    }

    @Test
    fun `should increase from 0 to 2 cards in players hand when giving first hand`() {
        val dealer = Dealer()
        val player = listOf<Player>(Player("Loli"))
        assertThat(player[0].cardsInHand.cards.size).isEqualTo(0)
        dealer.dealInitialCardsToPlayers(Players(player))
        assertThat(player[0].cardsInHand.cards.size).isEqualTo(2)
    }

}
