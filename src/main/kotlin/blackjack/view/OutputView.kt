package blackjack.view

import blackjack.model.Player

object OutputView {
    fun displayErrorMessages(message: String?) {
        println("[Error]::${message ?: ""}")
    }

    fun displayNamesOfPlayers(players: List<Player>) {
        val nameList = players.joinToString(", ") { it.name }
        println("\nDealing two cards to dealer, $nameList.")
    }

    fun displayCardsOfPlayers(player: Player) {
        println(getCardsOfPlayers(player))
    }

    private fun getCardsOfPlayers(player: Player): String {
        return "${player.name}'s card: " + player.cards.joinToString(", ")
    }

    fun displayCardsOfDealer(player: Player) {
        print("Dealer: ")
        println(player.cards[0].toString())
    }

    fun displayDealersTurn() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayCardsOfPlayersWithScore(player: Player) {
        val printableString =
            getCardsOfPlayers(player) + " – Total: ${player.score}"
        println(printableString)
    }

    fun displayFinalResult(
        dealer: Player,
        players: List<Player>,
    ) {
        println("## Final Earnings")
        println("Dealer: ${dealer.earning.toInt()}")
        players.forEach { println("${it.name}: ${it.earning.toInt()}") }
    }

    fun printEmptyLine() {
        println()
    }
}
