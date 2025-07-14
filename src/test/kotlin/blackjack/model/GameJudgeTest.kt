package blackjack.model

import blackjack.model.card.Card
import blackjack.model.participant.Dealer
import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import blackjack.model.result.Result
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameJudgeTest {
    private lateinit var dealer: Dealer
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var participants: Participants

    @BeforeEach
    fun setUp() {
        dealer = Dealer("Dealer")
        player1 = Player("Alice")
        player1.placeBet(100.0)
        player2 = Player("Bob")
        player2.placeBet(50.0)
        participants = Participants(dealer, listOf(player1, player2))
    }

    @Test
    fun `evaluateAll should update player and dealer results correctly`() {
        // Setup mock hands to control the result
        dealer.addCard(Card(Card.Suit.HEARTS, Card.Rank.TEN))
        dealer.addCard(Card(Card.Suit.HEARTS, Card.Rank.TEN))
        player1.addCard(Card(Card.Suit.HEARTS, Card.Rank.NINE))
        player1.addCard(Card(Card.Suit.HEARTS, Card.Rank.EIGHT))
        player2.addCard(Card(Card.Suit.HEARTS, Card.Rank.ACE))
        player2.addCard(Card(Card.Suit.HEARTS, Card.Rank.TEN))
        GameJudge.evaluateAll(participants)

        assertEquals(Result.LOSE.description, player1.result)
        assertEquals(Result.WIN.description, player2.result)
        assertThat(dealer.result).contains("Win")
        assertThat(dealer.result).contains("Lose")
    }

    @Test
    fun `evaluateAll should apply profit and loss correctly`() {
        dealer.addCard(Card(Card.Suit.HEARTS, Card.Rank.TEN))
        dealer.addCard(Card(Card.Suit.HEARTS, Card.Rank.TEN))
        player1.addCard(Card(Card.Suit.HEARTS, Card.Rank.ACE))
        player1.addCard(Card(Card.Suit.HEARTS, Card.Rank.TEN))
        player2.addCard(Card(Card.Suit.HEARTS, Card.Rank.NINE))
        player2.addCard(Card(Card.Suit.HEARTS, Card.Rank.SEVEN))

        GameJudge.evaluateAll(participants)

        assertTrue(player1.profit.value > 0)
        assertTrue(player2.profit.value < 0)
        val totalProfit = player1.profit + player2.profit
        assertEquals(-totalProfit.value, dealer.profit.value, 0.001)
    }
}
