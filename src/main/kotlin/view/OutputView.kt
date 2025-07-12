package view

import model.Dealer
import model.Player
import model.ResultStatus

object OutputView {
    fun displayInitialCards(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("Dealing two cards to dealer, ${players.joinToString(", ") { it.name }}.")
        println("Dealer: ${CardView.renderAll(dealer.showCards(), false)}")
        players.forEach { println("${it.name}'s cards: ${CardView.renderAll(it.showCards(), true)} ") }
    }

    fun displayPlayersTurn(player: Player) {
        println("${player.name}'s cards: ${CardView.renderAll(player.showCards(), true)}")
    }

    fun displayDealersGame() {
        println("Dealer draws one more card due to having 16 or less.")
    }

    fun displayFinalCardsOnHand(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("Dealer's cards: ${CardView.renderAll(dealer.showCards(), true)} - Total: ${dealer.getScore()}")
        players.forEach {
            println("${it.name}'s cards: ${CardView.renderAll(it.showCards(), true)} - Total: ${it.getScore()}")
        }
    }

    fun displayResults(
        results: List<ResultStatus>,
        players: List<Player>,
    ) {
        println("\n## Final Results")
        println(getDealersResult(results))
        for (i in players.indices) {
            println("${players[i].name}: ${ResultStatus.toText(results[i])}")
        }
    }

    private fun getDealersResult(playersResult: List<ResultStatus>): String {
        val wins = playersResult.count { it == ResultStatus.LOSS }
        val losses = playersResult.count { it == ResultStatus.WIN }
        val draws = playersResult.count { it == ResultStatus.DRAW }
        return "Dealer: $wins Wins $losses Losses $draws draws"
    }
}
