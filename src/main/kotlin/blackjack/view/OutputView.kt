package blackjack.view

import blackjack.Player

object OutputView {
    fun displayErrorMessages(message: String?) {
        println("[Error]::${message ?: ""}")
    }

    fun displayNamesOfPlayers(players: List<Player>) {
        val nameList = players.joinToString(", ") { it -> it.gamblerInfo.name }
        println("\nDealing two cards to dealer, $nameList.")
    }

//    fun displayCardsOfPlayers() {
//
//    }
//
//    fun displayCardsOfSinglePlayer(player: Player): String {
//
//    }

    fun displayDealersInitialCard(player: Player) {
        println(player.getCards())
//        val card = player.cards.first().toString()
        println("Dealer: ")
    }
}
