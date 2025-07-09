package view

import model.Card
import model.Dealer
import model.Player

object OutputView {
    fun displayInitialCards(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("Dealing two cards to dealer, ${players.joinToString(", ") { it.name }}.")
        println("Dealer: ${displayCards(dealer.showCards(), false)}")
        players.forEach { println("${it.name}'s cards: ${displayCards(it.showCards(), true)} ") }
    }

    fun displayPlayersTurn(player: Player) {
        println(player)
    }

    fun displayDealersGame() {
        println("Dealer draws one more card due to having 16 or less.")
    }

    private fun displayCards(
        cards: Set<Card>,
        showAll: Boolean,
    ): String {
        if (showAll) {
            return cards.joinToString(", ")
        }
        return cards.first().toString()
    }
}
