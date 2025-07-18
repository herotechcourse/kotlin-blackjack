package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Player

object OutputView {
    fun displayTableSetUp(
        dealer: Dealer,
        players: List<Player>,
    ) {
        displayDealerFirstCard(dealer)
        displayPlayersSetUp(players)
    }

    fun displayPlayersSetUp(players: List<Player>) {
        players.forEach { displayPlayer(it) }
    }

    fun displayDealerFirstCard(dealer: Dealer) {
        println("${dealer.name}'s cards: " + dealer.state.hand.cards[0].rank.face + dealer.state.hand.cards[0].suit.symbol)
    }

    private fun cardsText(cards: List<Card>): String {
        return cards.joinToString(", ") { it.rank.face + it.suit.symbol }
    }

    fun displayPlayer(player: Player) {
        println("${player.name}'s cards: " + cardsText(player.state.hand.cards))
    }

    fun displayDealerStats(dealer: Dealer) {
        println(" Dealer draws ${dealer.state.hand.size - 2} more card due to having 16 or less")
    }

    fun displayFinalResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("${dealer.name}'s cards: " + "${cardsText(dealer.state.hand.cards)} - Total: ${dealer.points}.")
        players.forEach {
            println("${it.name}'s cards: " + "${cardsText(it.state.hand.cards)} - Total: ${it.points}.")
        }
    }

    fun printFinalRates(result: Map<Player, Double>) {
        val header = "\n## Final Earning"
        val playerLines =
            result
                .entries
                .joinToString("\n") { (player, profit) -> "${player.name}: ${profit.toString().format("%.2f")}" }

        println(listOf(header, playerLines).joinToString("\n"))
    }
}
