package view

import model.Card
import model.Dealer
import model.Player
import model.ResultStatus

class OutputView {
    fun displayInitialCards(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("Dealing two cards to dealer, ${players.joinToString(", ") { it.name }}.")
        println("Dealer: ${displayCards(dealer.showCards(), false)}")
        players.forEach { println("${it.name}'s cards: ${displayCards(it.showCards(), true)} ") }
    }

    fun displayPlayerTurn(player: Player) {
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
        results: List<ResultStatus>,
        players: List<Player>,
    ) {
        println("\n## Final Results")
        println(getDealerResult(results))
        for (i in players.indices) {
            println("${players[i].name}: ${ResultStatus.toText(results[i])}")
        }
    }

    private fun getDealerResult(playersResult: List<ResultStatus>): String {
        val wins = playersResult.count { it == ResultStatus.LOSS }
        val losses = playersResult.count { it == ResultStatus.WIN }
        val draws = playersResult.count { it == ResultStatus.DRAW }
        return "Dealer: $wins Wins $losses Losses $draws draws"
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
