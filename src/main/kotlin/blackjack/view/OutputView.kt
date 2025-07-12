package blackjack.view

import blackjack.model.Participants
import blackjack.model.Player

object OutputView {
    private const val DEALER_DREW_MESSAGE = "Dealer draws one more card due to having 16 or less."
    private const val DEALER_STAND_MESSAGE = "Dealer stands directly."
    private const val RESULTS_HEAD = "## Final Results"

    fun printPlayerInfo(player: Player) {
        println(player)
    }

    fun printParticipantsHands(participants: Participants) {
        printEmptyLine()
        println("Dealing two cards to ${participants.dealer.name}, ${getPlayersNames(participants.players)}.")

        println(participants.dealer)
        participants.players.forEach(::println)

        printEmptyLine()
    }

    fun printDealersDrawMessage() {
        println(DEALER_DREW_MESSAGE)
        printEmptyLine()
    }

    fun printDealersStandMessage() {
        printEmptyLine()
        println(DEALER_STAND_MESSAGE)
        printEmptyLine()
    }

    fun printFinalHands(participants: Participants) {
        println("${participants.dealer} - Total: ${participants.dealerScore}")
        participants.players.forEach { println("$it - Total: ${it.getScore()}") }
    }

    fun printResults(participants: Participants) {
        printEmptyLine()
        println(RESULTS_HEAD)
        println("${participants.dealerName}: ${participants.dealerResult}")
        participants.players.forEach { println("${it.name}: ${it.result}") }
    }

    fun printErrorMessage(message: String) {
        println("\n[ERROR]: $message\n")
    }

    fun printEmptyLine() {
        println()
    }

    private fun getPlayersNames(players: List<Player>): String {
        return players.joinToString(", ") { it.name }
    }
}
