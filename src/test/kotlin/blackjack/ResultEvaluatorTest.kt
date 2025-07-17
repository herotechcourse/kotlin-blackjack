package blackjack

import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.HandCards
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Rank
import blackjack.model.ResultEvaluator
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ResultEvaluatorTest {
    private val deck = CardDeck()

    @Test
    fun `test dealer is not busted, player is not busted, DEALER WINS`() {
        val playersCards = mutableListOf(Card(Rank.ACE, Suit.DIAMONDS), Card(Rank.NINE, Suit.DIAMONDS)) // total 20
        val dealersCards = mutableListOf(Card(Rank.ACE, Suit.DIAMONDS), Card(Rank.KING, Suit.DIAMONDS)) // total 21
        val player = Player("test", HandCards(playersCards), 1000)
        val players = Players(listOf(player))
        val dealer = Dealer(deck = deck, players = players, handCards = HandCards(dealersCards))
        val evaluator = ResultEvaluator(players, dealer)
        val results = evaluator.calculateResults()
        assertThat(results.component1().wins).isEqualTo(1)
        assertThat(results.component1().losses).isEqualTo(0)
        assertThat(results.component2()[0].win).isEqualTo(false)
        assertThat(results.component2()[0].draw).isEqualTo(false)
    }

    @Test
    fun `test dealer is not busted, player is not busted, PLAYER WINS`() {
        val playersCards = mutableListOf(Card(Rank.ACE, Suit.DIAMONDS), Card(Rank.KING, Suit.DIAMONDS)) // total 21
        val dealersCards = mutableListOf(Card(Rank.ACE, Suit.DIAMONDS), Card(Rank.NINE, Suit.DIAMONDS)) // total 20
        val player = Player("test", HandCards(playersCards), 1000)
        val players = Players(listOf(player))
        val dealer = Dealer(deck = deck, players = players, handCards = HandCards(dealersCards))
        val evaluator = ResultEvaluator(players, dealer)
        val results = evaluator.calculateResults()
        assertThat(results.component1().wins).isEqualTo(0)
        assertThat(results.component1().losses).isEqualTo(1)
        assertThat(results.component2()[0].win).isEqualTo(true)
        assertThat(results.component2()[0].draw).isEqualTo(false)
    }

    @Test
    fun `test dealer is not busted, player is not busted, DRAW`() {
        val playersCards = mutableListOf(Card(Rank.TEN, Suit.DIAMONDS), Card(Rank.NINE, Suit.DIAMONDS)) // total 19
        val dealersCards = mutableListOf(Card(Rank.NINE, Suit.CLUBS), Card(Rank.TEN, Suit.SPADES)) // total 19
        val player = Player("test", HandCards(playersCards), 1000)
        val players = Players(listOf(player))
        val dealer = Dealer(deck = deck, players = players, handCards = HandCards(dealersCards))
        val evaluator = ResultEvaluator(players, dealer)
        val results = evaluator.calculateResults()
        assertThat(results.component1().wins).isEqualTo(0)
        assertThat(results.component1().losses).isEqualTo(0)
        assertThat(results.component2()[0].win).isEqualTo(false)
        assertThat(results.component2()[0].draw).isEqualTo(true)
    }

    @Test
    fun `test dealer is not busted, player is busted, DEALER WINS`() {
        val playersCards =
            mutableListOf(
                Card(Rank.KING, Suit.HEARTS),
                Card(Rank.NINE, Suit.CLUBS),
                Card(Rank.FIVE, Suit.SPADES),
            ) // total 24 busted
        val dealersCards = mutableListOf(Card(Rank.TEN, Suit.DIAMONDS), Card(Rank.NINE, Suit.DIAMONDS)) // total 19
        val player = Player("test", HandCards(playersCards), 1000)
        val players = Players(listOf(player))
        val dealer = Dealer(deck = deck, players = players, handCards = HandCards(dealersCards))
        val evaluator = ResultEvaluator(players, dealer)
        val results = evaluator.calculateResults()
        assertThat(results.component1().wins).isEqualTo(1)
        assertThat(results.component1().losses).isEqualTo(0)
        assertThat(results.component2()[0].win).isEqualTo(false)
        assertThat(results.component2()[0].draw).isEqualTo(false)
    }

    @Test
    fun `test dealer is busted, player is not busted, PLAYER WINS`() {
        val playersCards = mutableListOf(Card(Rank.NINE, Suit.CLUBS), Card(Rank.KING, Suit.SPADES)) // total 19
        val dealersCards =
            mutableListOf(
                Card(Rank.KING, Suit.HEARTS),
                Card(Rank.NINE, Suit.DIAMONDS),
                Card(Rank.FIVE, Suit.CLUBS),
            ) // total 24 busted
        val player = Player("test", HandCards(playersCards), 1000)
        val players = Players(listOf(player))
        val dealer = Dealer(deck = deck, players = players, handCards = HandCards(dealersCards))
        val evaluator = ResultEvaluator(players, dealer)
        val results = evaluator.calculateResults()
        assertThat(results.component1().wins).isEqualTo(0)
        assertThat(results.component1().losses).isEqualTo(1)
        assertThat(results.component2()[0].win).isEqualTo(true)
        assertThat(results.component2()[0].draw).isEqualTo(false)
    }

    @Test
    fun `test dealer is busted, player is busted, LOSS FOR PLAYER, DEALER WINS`() {
        val playersCards =
            mutableListOf(
                Card(Rank.KING, Suit.HEARTS),
                Card(Rank.NINE, Suit.CLUBS),
                Card(Rank.FIVE, Suit.SPADES),
            ) // 24 busted
        val dealersCards =
            mutableListOf(
                Card(Rank.KING, Suit.DIAMONDS),
                Card(Rank.NINE, Suit.HEARTS),
                Card(Rank.SIX, Suit.CLUBS),
            ) // 25 busted
        val player = Player("test", HandCards(playersCards), 1000)
        val players = Players(listOf(player))
        val dealer = Dealer(deck = deck, players = players, handCards = HandCards(dealersCards))
        val evaluator = ResultEvaluator(players, dealer)
        val results = evaluator.calculateResults()
        assertThat(results.component1().wins).isEqualTo(1)
        assertThat(results.component1().losses).isEqualTo(0)
        assertThat(results.component2()[0].win).isEqualTo(false)
        assertThat(results.component2()[0].draw).isEqualTo(false)
    }
}
