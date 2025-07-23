package blackjack.view

import blackjack.Constants.DEALER
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
        return "${player.name}'s cards: " + player.cards.joinToString(", ")
    }

    fun displayCardsOfDealer(player: Player) {
        println("Dealer: ${player.cards[0]}")
    }

    fun displayDealersTurn() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayCards(
        dealer: Player,
        players: List<Player>,
    ) {
        displayCardsOfPlayersWithScore(dealer)
        players.forEach(OutputView::displayCardsOfPlayersWithScore)
    }

    private fun displayCardsOfPlayersWithScore(player: Player) {
        if (player.name == DEALER) {
            println()
        }
        val printableString =
            getCardsOfPlayers(player) + " – Total: ${player.score}"
        println(printableString)
    }

    fun displayFinalResult(
        dealer: Player,
        players: List<Player>,
    ) {
        println()
        println("## Final Earnings")
        println("Dealer: ${dealer.earning.toInt()}")
        players.forEach { println("${it.name}: ${it.earning.toInt()}") }
    }
}
