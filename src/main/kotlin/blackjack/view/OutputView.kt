package blackjack.view

import blackjack.Card
import blackjack.Dealer
import blackjack.Player
import blackjack.ScoreCalculator

object OutputView {

    fun displayDealing(players: List<String>) {
        println("\nDealing two cards to dealer, ${players.joinToString(", ")}.")
    }

    fun displayPlayerHand(player: Player) {
        println("${player.name}'s cards: ${player.displayHand()}")
    }

    fun displayPlayerHands(players: List<Player>) {
        players.forEach { println("${it.name}'s cards: ${it.displayHand()}") }
    }

    fun displayDealerFirstTwoCards(cards: List<Card>) {
        println("Dealer: ${cards[0].display()}, ${cards[1].display()}")
    }

    fun displayDealerDraw() {
        println("Dealer draws one more card due to having 16 or less.")
    }

    fun displayDealerStatus(dealer: Dealer) {
        println("Dealer's cards: ${dealer.displayHand()} – Total: ${ScoreCalculator.calculate(dealer)}")
    }

    fun displayPlayerStatus(player: Player) {
        println("${player.name}'s cards: ${player.displayHand()} – Total: ${ScoreCalculator.calculate(player)}")
    }

    fun displayFinalResultsHeader() {
        println("\n## Final Results")
    }

    fun displayDealerResult(wins: Int, losses: Int) {
        println("Dealer: $wins Win $losses Lose")
    }

    fun displayPlayerResult(name: String, result: String) {
        println("$name: $result")
    }

    fun displayLineBreak() {
        println()
    }
}
