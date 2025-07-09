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

}