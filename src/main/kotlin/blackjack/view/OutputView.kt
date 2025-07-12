package blackjack.view

import blackjack.model.Participants
import blackjack.model.Player

object OutputView {
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
        println("Dealer draws one more card due to having 16 or less.")
        printEmptyLine()
    }

    fun printDealersStandMessage() {
        printEmptyLine()
        println("Dealer stands directly.")
        printEmptyLine()
    }

    fun printFinalHands(participants: Participants) {
        println("${participants.dealer} - Total: ${participants.dealerScore}")
        participants.players.forEach { println("$it - Total: ${it.getScore()}") }
    }

    fun printResults(participants: Participants) {
        printEmptyLine()
        println("## Final Results")
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
