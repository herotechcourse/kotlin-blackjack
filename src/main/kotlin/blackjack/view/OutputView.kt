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
        printDealerHand(dealer)

        for (player in players) {
            println(player)
        }

        printEmptyLine()
    }

    fun printDealerHand(dealer: Dealer) {
        val output =
            if (dealer.isShowingAllCards()) {
                "${dealer.name}'s cards: ${dealer.getDealtCards()}"
            } else if (dealer.getDealtCards().isEmpty()) {
                "${dealer.name} has no cards yet."
            } else {
                "${dealer.name}: ${dealer.getDealtCards()[0]}"
            }
        println(output)
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

    fun printPlayersWinnings(players: List<Player>) {
        players.forEach { player -> println("${player.name}: ${player.finalEarnings}") }
    }

    fun printDealersWinnings(dealer: Dealer) {
        println("${dealer.name}: ${dealer.finalEarnings}")
    }

    fun printWinnings(
        players: List<Player>,
        dealer: Dealer,
    ) {
        printEmptyLine()
        println("## Final Earnings ")
        printDealersWinnings(dealer)
        printPlayersWinnings(players)
    }

    private fun printEmptyLine() {
        println()
    }
}
