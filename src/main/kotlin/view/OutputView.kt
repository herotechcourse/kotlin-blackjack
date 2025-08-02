package view

import model.BasePlayer
import model.Card
import model.Dealer
import model.Player

class OutputView {
    fun displayInitialCards(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("Dealing two cards to dealer, ${players.joinToString(", ") { it.name }}.")
        println("Dealer: ${displayCards(dealer.showCards(), false)}")
        players.forEach { println("${it.name}'s cards: ${displayCards(it.showCards(), true)} ") }
    }

    fun displayPlayerTurn(player: BasePlayer) {
        println(player)
    }

    fun displayDealerGame() {
        println("Dealer draws one more card due to having 16 or less.")
    }

    fun displayFinalCardsOnHand(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("Dealer's cards: ${displayCards(dealer.showCards(), true)} - Total: ${dealer.getScore()}")
        players.forEach {
            println("${it.name}'s cards: ${displayCards(it.showCards(), true)} - Total: ${it.getScore()}")
        }
    }

    fun displayResults(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("\n## Final Results")
        println("Dealer: ${dealer.earning}")
        players.forEach { println("${it.name}: ${it.earning}") }
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
