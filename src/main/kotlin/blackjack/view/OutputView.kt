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
        println("${dealer.name}: ${dealer.result}")
        players.forEach { println("${it.name}: ${it.result}") }
    }

    fun showErrorMessage(message: String) {
        println("[ERROR]: $message")
    }

    private fun getPlayersNames(players: List<Player>): String {
        return players.joinToString(", ") { it.name }
    }

    private fun printEmptyLine() {
        println()
    }
}
