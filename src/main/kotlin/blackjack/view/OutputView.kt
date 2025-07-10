package blackjack.view

import blackjack.model.Player
import blackjack.model.Statistics

object OutputView {
    fun printAllPlayers(players: List<Player>) {
        players.forEach { printOnePlayer(it) }
    }

    fun printOnePlayer(player: Player) {
        println("${player.name}'s cards: " + player.cardsToString())
    }

    fun printDealerDrawsCards(player: Player) {
        println("Dealer draws ${player.numberInHand() - 1} more card due to having 16 or less.")
    }

    fun printAskForCard(player: Player) {
        println("Would ${player.name} like to draw another card? (y for yes, n for no)")
    }

    fun printOnePlayerFinalResult(player: Player) {
        println("${player.name}'s cards: " + "${player.cardsToString()} - Total: ${player.calculatePoints()}.")
    }

    fun printFinalResults(players: List<Player>) {
        players.forEach { printOnePlayerFinalResult(it) }
    }

    private fun printWinOrLose(condition: Boolean): String {
        return when (condition) {
            true -> "Win"
            false -> "Lose"
        }
    }

    fun printStatistics(statistics: Statistics) {
        println("\n## Final Results")
        println("Dealer: ${statistics.dealerWin} Win ${statistics.dealerLose} Lose")
        statistics.totalResult.forEach {
                (player, winnings) ->
            println("${player.name}: ${printWinOrLose(winnings == 1)}")
        }
    }
}
