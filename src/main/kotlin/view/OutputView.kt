package view

import model.Dealer
import model.Player
import model.Players
import model.ResultStatus

object OutputView {
    fun displayInitialCards(
        allPlayers: Players,
        dealer: Dealer,
    ) {
        println("Dealing two cards to dealer, ${allPlayers.players.joinToString(", ") { it.name }}.")
        println("Dealer: ${CardView.renderAll(dealer.showCards(), false)}")
        allPlayers.players.forEach { println("${it.name}'s cards: ${CardView.renderAll(it.showCards(), true)} ") }
    }

    fun displayPlayersTurn(player: Player) {
        println("${player.name}'s cards: ${CardView.renderAll(player.showCards(), true)}")
    }

    fun displayDealersGame() {
        println("Dealer draws one more card due to having 16 or less.")
    }

    fun displayFinalCardsOnHand(
        allPlayers: Players,
        dealer: Dealer,
    ) {
        println("Dealer's cards: ${CardView.renderAll(dealer.showCards(), true)} - Total: ${dealer.getScore()}")
        allPlayers.players.forEach {
            println("${it.name}'s cards: ${CardView.renderAll(it.showCards(), true)} - Total: ${it.getScore()}")
        }
    }

    fun displayResults(
        results: List<ResultStatus>,
        allPlayers: Players
    ) {
        println("\n## Final Results")
        println(getDealersResult(results))
        for (i in allPlayers.players.indices) {
            println("${allPlayers.players[i].name}: ${ResultStatus.toText(results[i])}")
        }
    }

    fun displayFinalEarnings(allPlayers: Players, dealer:Dealer) {
        println("\n## Final Earnings")
        println("Dealer: ${dealer.earnings.toInt()}")
        for (i in allPlayers.players.indices) {
            println("${allPlayers.players[i].name}: ${(allPlayers.players[i].earnings).toInt()}")
        }
    }

    private fun getDealersResult(playersResult: List<ResultStatus>): String {
        val wins = playersResult.count { it == ResultStatus.LOSS }
        val losses = playersResult.count { it == ResultStatus.WIN }
        val draws = playersResult.count { it == ResultStatus.DRAW }
        return "Dealer: $wins Wins $losses Losses $draws draws"
    }
}
