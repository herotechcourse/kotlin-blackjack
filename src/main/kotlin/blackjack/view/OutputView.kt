package blackjack.view

import blackjack.model.PlayerBackup
import blackjack.model.Statistics

object OutputView {
    fun printAllPlayers(players: List<PlayerBackup>) {
        players.forEach { printOnePlayer(it) }
    }

    fun printOnePlayer(player: PlayerBackup) {
        println("${player.name}'s cards: " + player.cardsToString())
    }

    fun printDealerDrawsCards(player: PlayerBackup) {
        println("Dealer draws ${player.numberInHand() - 1} more card due to having 16 or less.")
    }

    fun printAskForCard(player: PlayerBackup) {
        println("Would ${player.name} like to draw another card? (y for yes, n for no)")
    }

    fun printOnePlayerFinalResult(player: PlayerBackup) {
        println("${player.name}'s cards: " + "${player.cardsToString()} - Total: ${player.calculatePoints()}.")
    }

    private fun printFinalResults(statistics: Statistics) {
        val players = statistics.dealer + statistics.players
        players.forEach { printOnePlayerFinalResult(it) }
    }

    private fun printWinOrLose(condition: Boolean): String {
        return when (condition) {
            true -> "Win"
            false -> "Lose"
        }
    }

    fun printStatistics(statistics: Statistics) {
        printFinalResults(statistics)

        println("\n## Final Results")
        println("Dealer: ${statistics.dealerWin} Win ${statistics.dealerLose} Lose")
        statistics.totalResult.forEach {
                (player, winnings) ->
            println("${player.name}: ${printWinOrLose(winnings == 1)}")
        }
    }

    fun printError(msg: String?) {
        println("Error: $msg")
    }

    object Message {
        const val ENTER_PLAYERS_NAMES = "Enter the names of the players (comma-separated):"
        const val GENERATE_NEW_CARD = " Card deck is empty... prepare for a new card deck."
    }
}
