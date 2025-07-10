package blackjack.view

import blackjack.model.Player

object OutputView {
    fun printAllPlayers(players: List<Player>) {
        players.forEach { printOnePlayer(it) }
    }

    fun printOnePlayer(player: Player) {
        println("${player.name}'s cards: " + "${player.cards}")
    }

    fun printDealerDrawsCards(player: Player) {
        println("Dealer draws ${player.numberInHand() - 1} more card due to having 16 or less.")
    }

    fun printAskForCard(player: Player) {
        println("Would ${player.name} like to draw another card? (y for yes, n for no)")
    }
}
