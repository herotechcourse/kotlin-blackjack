package blackjack

import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.HandCards
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Rank
import blackjack.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class DealerTest {
    private val deck = CardDeck()
    private val playerCards = mutableListOf(Card(Rank.ACE, Suit.DIAMONDS), Card(Rank.KING, Suit.DIAMONDS))
    private val player = Player("Lisa", handCards = HandCards(playerCards))

    @Test
    fun `dealer should draw when 16`() {
        val dealer = Dealer(deck = deck, players = Players(listOf(player)))
        dealer.handCards.add(Card(Rank.ACE, Suit.DIAMONDS))
        dealer.handCards.add(Card(Rank.FIVE, Suit.DIAMONDS))
        assertTrue { dealer.shouldDraw() }
    }

    @Test
    fun `dealer should not draw when 17`() {
        val dealer = Dealer(deck = deck, players = Players(listOf(player)))
        dealer.handCards.add(Card(Rank.ACE, Suit.DIAMONDS))
        dealer.handCards.add(Card(Rank.SIX, Suit.DIAMONDS))
        assertThat(dealer.shouldDraw()).isFalse()
    }

    @Test
    fun `dealer hand cards reduce total when necessary`() {
        val dealer = Dealer(deck = deck, players = Players(listOf(player)))
        dealer.handCards.add(Card(Rank.KING, Suit.DIAMONDS))
        dealer.handCards.add(Card(Rank.ACE, Suit.DIAMONDS))
        dealer.handCards.add(Card(Rank.FIVE, Suit.DIAMONDS))
        assertThat(dealer.handCards.total).isEqualTo(16)
    }
}
