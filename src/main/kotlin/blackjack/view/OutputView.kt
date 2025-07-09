package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Player

object OutputView {
    fun printParticipantsHands(players: List<Player>, dealer: Dealer) {
        println("Dealing two cards to ${dealer.name}, ${getPlayersNames(players)}.")
        println(dealer)

        for (player in players) {
            println(player)
        }
    }

    private fun getPlayersNames(players: List<Player>): String {
        return players.joinToString(", ") { it.name }
    }

    fun printDealersMessage() {
        println("Dealer draws one more card due to having 16 or less.")
    }

    fun printFinalHands(players: List<Player>, dealer: Dealer) {
        println("$dealer - Total: ${dealer.getScore()}")
        players.forEach { println("$it - Total: ${it.getScore()}")}
    }

}