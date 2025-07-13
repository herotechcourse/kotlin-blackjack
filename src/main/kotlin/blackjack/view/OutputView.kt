package blackjack.view

import blackjack.model.Card
import blackjack.model.Player
import blackjack.model.Statistics

object OutputView {
    fun printAllPlayers(players: List<Player>) {
        players.forEach { printOnePlayer(it) }
    }

    fun printOnePlayer(player: Player) {
        println("${player.name}'s cards: " + player.cards.joinToString(" ") { displayCard(it) })
    }

    fun printDealerDrawsCards(player: Player) {
        println("\nDealer draws ${player.numberInHand() - 1} more card due to having 16 or less.")
    }

    fun printOnePlayerFinalResult(player: Player) {
        println("${player.name}'s cards: " + "${player.cards.joinToString(" ") { displayCard(it) }} - Total: ${player.calculatePoints()}.")
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
        val header = "\n## Final Results"
        val dealerLine = "Dealer: ${statistics.dealerWin} Win ${statistics.dealerLose} Lose"
        val playerLines =
            statistics.calculatePlayersWinning()
                .entries
                .joinToString("\n") { "${it.key.name}: ${printWinOrLose(it.value == 1)}" }

        println(listOf(header, dealerLine, playerLines).joinToString("\n"))
    }

    fun displayCard(card: Card): String {
        return "${card.rank.face}${card.suit.symbol}"
    }
}
