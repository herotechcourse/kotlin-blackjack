package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Playable
import blackjack.model.Player

object OutputView {
    fun displayInitialState(
        players: List<Player>,
        dealer: Dealer,
    ) {
        val names = listOf(dealer.name) + players.map { it.name.trim() }
        val sentence = names.joinToString(", ")
        println("\nDealing two cards to $sentence.")
        println("${dealer.name}: ${dealer.hand.cards[0]}")
        players.forEach { displayCurrentHand(it) }
    }

    fun displayCurrentHand(player: Player) {
        val hand = player.hand
        println("${player.name}'s cards: ${hand.toText()}")
    }

    fun displayDealerDrawsCard(dealer: Dealer) {
        println("\n${dealer.name} draws one more card due to having 16 or less.")
    }

    fun displayFinalState(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("\n${dealer.name}'s cards: ${dealer.hand.toText()} – Total: ${dealer.calculateHand()}")
        players.forEach { player ->
            println("${player.name}'s cards: ${player.hand.toText()} – Total: ${player.calculateHand()}")
        }
    }

    fun displayFinalResults(
        winStatistics: StatsView,
        earnings: Map<Playable, Int>,
    ) {
        println("\n## Final Earnings")
        earnings.forEach { (player, amount) ->
            println("${player.name}: ${formatEarning(amount)}")
        }
    }

    private fun formatEarning(amount: Int): String {
        return amount.toString()
    }
}
