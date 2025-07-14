package blackjack

import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.HandCards
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Rank
import blackjack.model.Suit
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

class ParticipantTest {

    private val deck = CardDeck()

    private fun createPlayer(name: String, cards: List<Card>, bet: Int): Player {
        return Player(name, HandCards(cards.toMutableList()), bet)
    }

    private fun createDealer(cards: List<Card>, players: Players): Dealer {
        return Dealer(deck = deck, players = players, handCards = HandCards(cards.toMutableList()))
    }

    @Test
    fun `player busts and loses bet`() {
        val playerCards = listOf(Card(Rank.KING, Suit.HEARTS), Card(Rank.QUEEN, Suit.CLUBS), Card(Rank.TWO, Suit.SPADES)) // 22 bust
        val dealerCards = listOf(Card(Rank.NINE, Suit.HEARTS), Card(Rank.SEVEN, Suit.CLUBS)) // 16
        val player = createPlayer("Alice", playerCards, 1200)
        val players = Players(listOf(player))
        val dealer = createDealer(dealerCards, players)

        player.settlePlayerAndDealer(dealer)

        assertThat(player.earning).isEqualTo(-1200)
        assertThat(dealer.earning).isEqualTo(1200)
    }

    @Test
    fun `dealer busts and player wins bet`() {
        val playerCards = listOf(Card(Rank.NINE, Suit.HEARTS), Card(Rank.EIGHT, Suit.CLUBS)) // 17
        val dealerCards = listOf(Card(Rank.KING, Suit.HEARTS), Card(Rank.NINE, Suit.CLUBS), Card(Rank.THREE, Suit.SPADES)) // 22 bust
        val player = createPlayer("Bob", playerCards, 2500)
        val players = Players(listOf(player))
        val dealer = createDealer(dealerCards, players)

        player.settlePlayerAndDealer(dealer)

        assertThat(player.earning).isEqualTo(2500)
        assertThat(dealer.earning).isEqualTo(-2500)
    }

    @Test
    fun `both player and dealer blackjack results in draw`() {
        val playerCards = listOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.KING, Suit.CLUBS)) // blackjack
        val dealerCards = listOf(Card(Rank.ACE, Suit.SPADES), Card(Rank.QUEEN, Suit.DIAMONDS)) // blackjack
        val player = createPlayer("Charlie", playerCards, 3000)
        val players = Players(listOf(player))
        val dealer = createDealer(dealerCards, players)

        player.settlePlayerAndDealer(dealer)

        assertThat(player.earning).isEqualTo(0)
        assertThat(dealer.earning).isEqualTo(0)
    }

    @Test
    fun `player blackjack wins 1_5x bet`() {
        val playerCards = listOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.KING, Suit.CLUBS)) // blackjack
        val dealerCards = listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.SEVEN, Suit.DIAMONDS)) // 17
        val player = createPlayer("Diana", playerCards, 4000)
        val players = Players(listOf(player))
        val dealer = createDealer(dealerCards, players)

        player.settlePlayerAndDealer(dealer)

        assertThat(player.earning).isEqualTo(6000) // 1.5 * 4000
        assertThat(dealer.earning).isEqualTo(-6000)
    }

    @Test
    fun `dealer blackjack player loses bet`() {
        val playerCards = listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.SEVEN, Suit.CLUBS)) // 17
        val dealerCards = listOf(Card(Rank.ACE, Suit.SPADES), Card(Rank.JACK, Suit.DIAMONDS)) // blackjack
        val player = createPlayer("Eve", playerCards, 1500)
        val players = Players(listOf(player))
        val dealer = createDealer(dealerCards, players)

        player.settlePlayerAndDealer(dealer)

        assertThat(player.earning).isEqualTo(-1500)
        assertThat(dealer.earning).isEqualTo(1500)
    }

    @Test
    fun `player total higher than dealer wins bet`() {
        val playerCards = listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.NINE, Suit.CLUBS)) // 19
        val dealerCards = listOf(Card(Rank.EIGHT, Suit.SPADES), Card(Rank.SEVEN, Suit.DIAMONDS)) // 15
        val player = createPlayer("Frank", playerCards, 1800)
        val players = Players(listOf(player))
        val dealer = createDealer(dealerCards, players)

        player.settlePlayerAndDealer(dealer)

        assertThat(player.earning).isEqualTo(1800)
        assertThat(dealer.earning).isEqualTo(-1800)
    }

    @Test
    fun `player total lower than dealer loses bet`() {
        val playerCards = listOf(Card(Rank.SEVEN, Suit.HEARTS), Card(Rank.EIGHT, Suit.CLUBS)) // 15
        val dealerCards = listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.NINE, Suit.DIAMONDS)) // 19
        val player = createPlayer("Grace", playerCards, 17000)
        val players = Players(listOf(player))
        val dealer = createDealer(dealerCards, players)

        player.settlePlayerAndDealer(dealer)

        assertThat(player.earning).isEqualTo(-17000)
        assertThat(dealer.earning).isEqualTo(17000)
    }

    @Test
    fun `player and dealer tie results in push`() {
        val playerCards = listOf(Card(Rank.TEN, Suit.HEARTS), Card(Rank.NINE, Suit.CLUBS)) // 19
        val dealerCards = listOf(Card(Rank.NINE, Suit.SPADES), Card(Rank.TEN, Suit.DIAMONDS)) // 19
        val player = createPlayer("Hank", playerCards, 90000)
        val players = Players(listOf(player))
        val dealer = createDealer(dealerCards, players)

        player.settlePlayerAndDealer(dealer)

        assertThat(player.earning).isEqualTo(0)
        assertThat(dealer.earning).isEqualTo(0)
    }
}


