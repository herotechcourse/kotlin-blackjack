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

        players.forEach(::println)
        printEmptyLine()
    }

    fun printDealersDrawMessage() {
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

    fun printResults(
        players: List<Player>,
        dealer: Dealer,
    ) {
        printEmptyLine()
        println("## Final Results")
        println(getResultOf(dealer))
        players.forEach { println(getResultOf(it)) }
    }

    fun showErrorMessage(message: String) {
        println(message)
    }

    private fun getPlayersNames(players: List<Player>): String {
        return players.joinToString(", ") { it.name }
    }

    private fun getResultOf(dealer: Dealer): String {
        val winText = if (dealer.gameResults.wins > 0) "${dealer.gameResults.wins} Win" else ""
        val loseText = if (dealer.gameResults.loses > 0) "${dealer.gameResults.loses} Lose" else ""
        val tieText = if (dealer.gameResults.ties > 0) "${dealer.gameResults.ties} Tie" else ""

        return "$dealer.name: $winText $loseText $tieText"
    }

    private fun getResultOf(player: Player): String {
        return when {
            player.gameResults.wins > 0 -> "$player.name: Win"
            player.gameResults.loses > 0 -> "$player.name: Lose"
            else -> "$player.name: Tie"
        }
    }

    private fun printEmptyLine() {
        println()
    }
}
