package blackjack

import blackjack.model.Card
import blackjack.model.FinalResult
import blackjack.model.Player
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FinalResultTest {
    @Test
    fun `test blackjack payout scenarios`() {
        // Test case: dealer busts, p1 wins, p2 has blackjack
        val dealer = Player("dealer")
        val p1 = Player("p1", 100)
        val p2 = Player("p2", 200)

        dealer.addCard(
            listOf(
                Card(Rank.KING, Suit.SPADE),
                Card(Rank.QUEEN, Suit.HEART),
                Card(Rank.FIVE, Suit.DIAMOND),
            ),
        )
        p1.addCard(listOf(Card(Rank.TEN, Suit.SPADE), Card(Rank.NINE, Suit.HEART)))
        p2.addCard(listOf(Card(Rank.ACE, Suit.DIAMOND), Card(Rank.KING, Suit.HEART)))

        val finalResult = FinalResult(dealer, listOf(p1, p2))
        finalResult.updateEarnings()

        assertThat(p1.earning.toInt()).isEqualTo(100) // Normal win
        assertThat(p2.earning.toInt()).isEqualTo(300) // Blackjack win (1.5x)
        assertThat(dealer.earning.toInt()).isEqualTo(-400)
    }

    @Test
    fun `test normal win and loss scenarios`() {
        // Test case: dealer's score is 18, p1 wins with 19, p2 loses with 17
        val dealer = Player("dealer")
        val p1 = Player("p1", 100)
        val p2 = Player("p2", 200)

        dealer.addCard(listOf(Card(Rank.TEN, Suit.SPADE), Card(Rank.EIGHT, Suit.HEART)))
        p1.addCard(listOf(Card(Rank.TEN, Suit.DIAMOND), Card(Rank.NINE, Suit.SPADE)))
        p2.addCard(listOf(Card(Rank.TEN, Suit.HEART), Card(Rank.SEVEN, Suit.CLUB)))

        val finalResult = FinalResult(dealer, listOf(p1, p2))
        finalResult.updateEarnings()

        assertThat(p1.earning.toInt()).isEqualTo(100)
        assertThat(p2.earning.toInt()).isEqualTo(-200)
        assertThat(dealer.earning.toInt()).isEqualTo(100)
    }

    @Test
    fun `test tie lose and normal win scenarios`() {
        // Test case: dealer 18, p1 ties with 18, p2 loses with 17, p3 wins with 20
        val dealer = Player("dealer")
        val p1 = Player("p1", 100)
        val p2 = Player("p2", 200)
        val p3 = Player("p3", 150)

        dealer.addCard(listOf(Card(Rank.TEN, Suit.SPADE), Card(Rank.EIGHT, Suit.HEART)))
        p1.addCard(listOf(Card(Rank.TEN, Suit.DIAMOND), Card(Rank.EIGHT, Suit.CLUB)))
        p2.addCard(listOf(Card(Rank.TEN, Suit.HEART), Card(Rank.SEVEN, Suit.CLUB)))
        p3.addCard(listOf(Card(Rank.KING, Suit.SPADE), Card(Rank.QUEEN, Suit.HEART)))

        val finalResult = FinalResult(dealer, listOf(p1, p2, p3))
        finalResult.updateEarnings()

        assertThat(p1.earning.toInt()).isEqualTo(0) // Tie
        assertThat(p2.earning.toInt()).isEqualTo(-200) // Lose
        assertThat(p3.earning.toInt()).isEqualTo(150) // Normal win
        assertThat(dealer.earning.toInt()).isEqualTo(50)
    }
}
