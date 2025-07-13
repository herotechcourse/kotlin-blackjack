package blackjack.view

import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Participants
import blackjack.model.result.GameResult
import blackjack.model.result.Outcome

import blackjack.model.PlayerBackup
import blackjack.model.Statistics

object OutputView {
    fun printAllPlayers(players: List<PlayerBackup>) {
        players.forEach { printOnePlayer(it) }
    }

    fun printOnePlayer(player: PlayerBackup) {
        println("${player.name}'s cards: " + player.cardsToString())
    }

    fun showDealerDraw(dealer: Dealer) {
        println("\nDealer draws ${dealer.cardsCount() - 1} more card due to having 16 or less.\n")
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

    fun showError(msg: String?) {
        println("Error: $msg")
    }

    fun showGameResult(gameResult: GameResult) {
        val playersResults = gameResult.playersResults
        showCards(gameResult.dealer, showPoints(gameResult.dealer))
        playersResults.forEach { (player, _) ->
            showCards(player, showPoints(player))
        }

        println("\n${Message.FINAL_RESULTS_TITLE}")

        val dealerWin = playersResults.filter { it.value == Outcome.LOSE}.size
        val dealerLose = playersResults.filter { it.value == Outcome.WIN }.size
        val dealerDraw = playersResults.size - dealerLose - dealerWin
        val showDraw = if (dealerDraw > 0) ", $dealerDraw Draw" else ""

        println("Dealer: $dealerWin Win $dealerLose Lose$showDraw")

        playersResults.forEach { (player, outcome) ->
            println("${player.name}: ${outcome.name}")
        }
    }

    private fun showPoints(participant: Participant): String {
        return " - Total: ${participant.points}"
    }


    object Message {
        const val ENTER_PLAYERS_NAMES = "Enter the names of the players (comma-separated):"
        const val GENERATE_NEW_CARD = " Card deck is empty... prepare for a new card deck."
        const val FINAL_RESULTS_TITLE = "## Final Results"
    }
}
