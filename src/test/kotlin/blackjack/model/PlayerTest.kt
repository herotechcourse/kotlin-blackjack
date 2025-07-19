package blackjack.model

import blackjack.state.Blackjack
import blackjack.state.Bust
import blackjack.state.FirstTurn
import blackjack.state.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    fun `should initialize a Player with a valid name string`() {
        assertThat(Player("bojana")).isInstanceOf(Player::class.java)
    }

    @Test
    fun `should throw an error if player name is empty`() {
        assertThrows<IllegalArgumentException> { Player("") }
    }

    @Test
    fun `should draw a card in the first round`() {
        val player = Player("bojana")
        val card = Card(suit = Suit.SPADES, rank = Rank.KING)
        player.draw(card)

        assertThat(player.handState).isInstanceOf(FirstTurn::class.java)
        assertThat(player.handState.hand.cards).containsExactly(card)
        assertThat(player.handState.hand.size).isEqualTo(1)
        assertThat(player.points).isEqualTo(card.rank.value)
    }

    @Test
    fun `should draw a card in the second round and continue to Hit if not BlackJack`() {
        val player = Player("bojana")
        val card1 = Card(suit = Suit.SPADES, rank = Rank.FIVE)
        val card2 = Card(suit = Suit.HEARTS, rank = Rank.EIGHT)

        player.draw(card1)
        player.draw(card2)

        assertThat(player.handState).isInstanceOf(Hit::class.java)
        assertThat(player.handState.hand.cards).containsExactly(card1, card2)
        assertThat(player.handState.hand.size).isEqualTo(2)
        assertThat(player.points).isEqualTo(13)
    }

    @Test
    fun `should draw a card in the second round and continue to BlackJack if 21`() {
        val player = Player("bojana")
        val card1 = Card(suit = Suit.HEARTS, rank = Rank.ACE)
        val card2 = Card(suit = Suit.SPADES, rank = Rank.KING)

        player.draw(card1)
        player.draw(card2)

        assertThat(player.handState).isInstanceOf(Blackjack::class.java)
        assertThat(player.handState.hand.cards).containsExactly(card1, card2)
        assertThat(player.handState.hand.size).isEqualTo(2)
        assertThat(player.points).isEqualTo(21)
    }

    @Test
    fun `should draw a card and continue to Bust if bigger than 21`() {
        val player = Player("bojana")
        val card1 = Card(suit = Suit.HEARTS, rank = Rank.QUEEN)
        val card2 = Card(suit = Suit.SPADES, rank = Rank.KING)
        val card3 = Card(suit = Suit.SPADES, rank = Rank.THREE)

        player.draw(card1)
        player.draw(card2)
        player.draw(card3)

        assertThat(player.handState).isInstanceOf(Bust::class.java)
        assertThat(player.handState.hand.cards).containsExactly(card1, card2, card3)
        assertThat(player.handState.hand.size).isEqualTo(3)
        assertThat(player.points).isEqualTo(23)
    }

    @Test
    fun `should throw IllegalStateException if drawing in Bust state`() {
        val player = Player("bojana")
        val card1 = Card(suit = Suit.HEARTS, rank = Rank.QUEEN)
        val card2 = Card(suit = Suit.SPADES, rank = Rank.KING)
        val card3 = Card(suit = Suit.SPADES, rank = Rank.THREE)

        player.draw(card1)
        player.draw(card2)
        player.draw(card3)

        assertThat(player.handState).isInstanceOf(Bust::class.java)

        val card4 = Card(suit = Suit.SPADES, rank = Rank.KING)
        assertThrows<IllegalStateException> { player.draw(card4) }
    }

    @Test
    fun `should throw IllegalStateException if drawing in Blackjack state`() {
        val player = Player("bojana")
        val card1 = Card(suit = Suit.HEARTS, rank = Rank.QUEEN)
        val card2 = Card(suit = Suit.SPADES, rank = Rank.ACE)

        player.draw(card1)
        player.draw(card2)

        assertThat(player.handState).isInstanceOf(Blackjack::class.java)

        val card3 = Card(suit = Suit.SPADES, rank = Rank.KING)
        assertThrows<IllegalStateException> { player.draw(card3) }
    }

    @Test
    fun `should throw IllegalStateException if drawing in a Stay state`() {
        val player = Player("bojana")
        val card1 = Card(suit = Suit.HEARTS, rank = Rank.EIGHT)
        val card2 = Card(suit = Suit.SPADES, rank = Rank.ACE)
        player.draw(card1)
        player.draw(card2)
        player.stay()
        val card3 = Card(suit = Suit.SPADES, rank = Rank.ACE)

        assertThat(player.handState).isInstanceOf(blackjack.state.Stay::class.java)
        assertThrows<IllegalStateException> { player.draw(card3) }
    }
}
