package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.utils.Constants

object OutputView {
    fun displayInitialState(
        players: List<Player>,
        dealer: Dealer,
    ) {
        var sentence = "${dealer.name}, "
        val names = players.map { it.name }
        sentence += names.joinToString(", ")
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
        dealer: Dealer,
    ) {
        val playersResult = winStatistics.playerBoard
        val dealerResult = winStatistics.dealerStats
        println("\n## Final Results")
        when (dealerResult["tie"]) {
            0 -> println("${dealer.name}: ${dealerResult["win"]} Win ${dealerResult["lose"]} Lose")
            else -> println("${dealer.name}: ${dealerResult["win"]} Win ${dealerResult["lose"]} Lose ${dealerResult["tie"]} Tie")
        }
        playersResult.keys.forEach { player ->
            val result = givePlayerResult(player, playersResult)
            println("${player.name}: $result")
        }
    }

    private fun givePlayerResult(
        player: Player,
        playersResult: Map<Player, Int>,
    ): String {
        return when (playersResult[player]) {
            Constants.LOSE -> "Lose"
            Constants.WIN -> "Win"
            Constants.TIE -> "Tie"
            else -> "Error"
        }
    }
}
