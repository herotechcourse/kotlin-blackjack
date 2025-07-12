package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Player

object OutputView {
    fun printParticipantsHands(
        players: List<Player>,
        dealer: Dealer,
    ) {
        printEmptyLine()
        println("Dealing two cards to ${dealer.name}, ${getPlayersNames(players)}.")
        println(dealer)

        for (player in players) {
            println(player)
        }

        printEmptyLine()
    }

    private fun getPlayersNames(players: List<Player>): String {
        return players.joinToString(", ") { it.name }
    }

    fun printDealersDrawMessage() {
        printEmptyLine()
        println("Dealer draws one more card due to having 16 or less.")
        printEmptyLine()
    }

    fun printDealersStandMessage() {
        printEmptyLine()
        println("Dealer stands directly.")
        printEmptyLine()
    }

    fun printFinalHands(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("$dealer - Total: ${dealer.getScore()}")
        players.forEach { println("$it - Total: ${it.getScore()}") }
    }

    fun printPlayersResults(players: List<Player>) {
        players.forEach { player ->
            val results = when {
                player.gameResults.wins > 0 -> "${player.name} Win"
                player.gameResults.loses > 0 -> "${player.name}: Lose"
                else -> "${player.name}: Tie"
            }
            println(results)
        }
    }

    fun printDealerResults(dealer: Dealer) {
        val winText = if (dealer.gameResults.wins > 0) "${dealer.gameResults.wins} Win" else ""
        val loseText = if (dealer.gameResults.loses > 0) "${dealer.gameResults.loses} Lose" else ""
        val tieText = if (dealer.gameResults.ties > 0) "${dealer.gameResults.ties} Tie" else ""

        println("${dealer.name}: $winText $loseText $tieText")
    }

    fun printResults(
        players: List<Player>,
        dealer: Dealer,
    ) {
        printEmptyLine()
        println("## Final Results")
        printDealerResults(dealer)
        printPlayersResults(players)
    }

    private fun printEmptyLine() {
        println()
    }
}
