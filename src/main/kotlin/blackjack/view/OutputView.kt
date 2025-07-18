package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Hand
import blackjack.model.Player
import blackjack.model.PlayingCard
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
        println("Dealer: ${cardToText(dealer.hand.cards[0])}")
        players.forEach { displayCurrentHand(it) }
    }

    fun displayCurrentHand(player: Player) {
        val hand = player.hand
        println("${player.name}'s cards: ${handToText(hand)}")
    }

    fun displayDealerDrawsCard() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayFinalState(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("\nDealer's cards: ${handToText(dealer.hand)} – Total: ${dealer.calculateHand()}")
        players.forEach { player ->
            println("${player.name}'s cards: ${handToText(player.hand)} – Total: ${player.calculateHand()}")
        }
    }

    fun displayFinalResults(winStatistics: Stats) {
        val dealerResult = winStatistics.dealerStats
        println("\n## Final Results")
        when (dealerResult[Result.TIE]) {
            0 -> println("Dealer: ${dealerResult[Result.WIN]} Win ${dealerResult[Result.LOSE]} Lose")
            else -> println("Dealer: ${dealerResult[Result.WIN]} Win ${dealerResult[Result.LOSE]} Lose ${dealerResult[Result.TIE]} Tie")
        }
        winStatistics.players.forEach { player ->
            val result = givePlayerResult(player)
            println("${player.name}: $result")
        }
    }

    private fun givePlayerResult(player: Player): String {
        return when (player.result) {
            Result.LOSE -> "Lose"
            Result.WIN -> "Win"
            Result.TIE -> "Tie"
            else -> "Error"
        }
    }

    private fun handToText(hand: Hand): String {
        return hand.cards.joinToString(", ") { cardToText(it) }
    }

    private fun cardToText(card: PlayingCard): String {
        return card.rank.digit + card.suit.form
    }
}
