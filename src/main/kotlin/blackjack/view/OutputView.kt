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

    fun printResults(
        players: List<Player>,
        dealer: Dealer,
    ) {
        printEmptyLine()
        println("## Final Results")
        println(dealer.getResults())
        players.forEach { println(it.getResults()) }
    }

    fun showErrorMessage(message: String) {
        println(message)
    }

    private fun printEmptyLine() {
        println()
    }
}
