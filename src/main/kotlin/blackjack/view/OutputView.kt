package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Result
import blackjack.model.Stats

object OutputView {
    fun displayInitialState(
        players: List<Player>,
        dealer: Dealer,
    ) {
        var sentence = "dealer, "
        val names = players.map { it.name }
        sentence += names.joinToString(", ")
        println("\nDealing two cards to $sentence.")
        println("Dealer: ${dealer.hand.cards[0].string}")
        players.forEach { displayCurrentHand(it) }
    }

    fun displayCurrentHand(player: Player) {
        val hand = player.hand
        println("${player.name}'s cards: ${hand.toText()}")
    }

    fun displayDealerDrawsCard() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayFinalState(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("\nDealer's cards: ${dealer.hand.toText()} – Total: ${dealer.calculateHand()}")
        players.forEach { player ->
            println("${player.name}'s cards: ${player.hand.toText()} – Total: ${player.calculateHand()}")
        }
    }

    fun displayFinalResults(winStatistics: Stats) {
        val playersResult = winStatistics.playerBoard
        val dealerResult = winStatistics.dealerStats
        println("\n## Final Results")
        when (dealerResult[Result.TIE]) {
            0 -> println("Dealer: ${dealerResult[Result.WIN]} Win ${dealerResult[Result.LOSE]} Lose")
            else -> println("Dealer: ${dealerResult[Result.WIN]} Win ${dealerResult[Result.LOSE]} Lose ${dealerResult[Result.TIE]} Tie")
        }
        playersResult.keys.forEach { player ->
            val result = givePlayerResult(player, playersResult)
            println("${player.name}: $result")
        }
    }

    private fun givePlayerResult(
        player: Player,
        playersResult: Map<Player, Result>,
    ): String {
        return when (playersResult[player]) {
            Result.LOSE -> "Lose"
            Result.WIN -> "Win"
            Result.TIE -> "Tie"
            else -> "Error"
        }
    }
}
