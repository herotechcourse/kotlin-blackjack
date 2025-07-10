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

    fun displayCardsOfPlayers(player: Player) {
        print(player.name + "'s card: ")
        println(player.cards.joinToString(", "))
    }

    fun displayDealersInitialCard(player: Player) {
        println(player.cards)
//        val card = player.cards.first().toString()
        println("Dealer: ")
    }
}
