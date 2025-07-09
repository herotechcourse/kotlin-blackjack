package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Player

object OutputView {
    fun displayInitialState(
        players: List<Player>,
        dealer: Dealer,
    ) {
        var sentence = "dealer"
        players.forEach { sentence += ", " + it.name }
        println("\nDealing two cards to $sentence.")
        println("Dealer: ${dealer.hand[0].name}")
        players.forEach { displayCurrentHand(it) }
    }

    fun displayCurrentHand(player: Player) {
        println("${player.name}'s cards: ${player.getStringOfHand()}")
    }

    fun displayDealerDrawsCard() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayFinalState(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("\nDealer's cards: ${dealer.getStringOfHand()} – Total: ${dealer.calculateHand()}")
        players.forEach { player ->
            println("${player.name}'s cards: ${player.getStringOfHand()} – Total: ${player.calculateHand()}")
        }
    }

    // TODO: implement function
//    fun displayFinalResults(winStatistics: Stats) {
//        println("\n## Final Results")
//        //  "Dealer: 1 Win 1 Lose"
//        // loop
//            //  "{player.name}: Win"
//            //  "{player.name}: Lose"
//    }
}
