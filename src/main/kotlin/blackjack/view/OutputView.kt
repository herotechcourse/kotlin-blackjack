package blackjack.view

import blackjack.Player

object OutputView {
    fun displayErrorMessages(message: String?) {
        println("[Error]::${message ?: ""}")
    }

    fun displayNamesOfPlayers(players: List<Player>) {
        val nameList = players.joinToString(", ") { it.gamblerInfo.name }
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

    fun displayFinalResultsHeading() {
        println("## Final Results")
    }

    fun displayPlayerResult(win: Int, lose: Int, draw: Int) {
        println("Dealer: $win Win $draw Draw $lose Lose")
    }

    fun displayPlayerResult(name: String, result: Boolean) {
        val printableString = "$name: " + if (result) "Win" else "Lose"
        println(printableString)
    }

    fun printEmptyLine() {
        println()
    }
}
