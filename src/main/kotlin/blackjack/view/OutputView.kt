package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Player

object OutputView {
    fun displayTableSetUp(
        dealer: Dealer,
        players: List<Player>,
    ) {
        displayDealerFirstCard(dealer)
        displayPlayers(players)
    }

    fun displayPlayers(players: List<Player>) {
        players.forEach { displayPlayer(it) }
    }

    fun displayDealerFirstCard(dealer: Dealer) {
        println("${dealer.name}'s cards: " + dealer.state.hand.cards[0].rank.face + dealer.state.hand.cards[0].suit.symbol)
    }

    private fun cardsText(cards: List<Card>): String {
        return cards.joinToString(", ") { it.rank.face + it.suit.symbol }
    }

    fun displayDealer(dealer: Dealer) {
        println("${dealer.name}'s cards: " + cardsText(dealer.state.hand.cards))
    }

    fun displayPlayer(player: Player) {
        println("${player.name}'s cards: " + cardsText(player.state.hand.cards))
    }
}
