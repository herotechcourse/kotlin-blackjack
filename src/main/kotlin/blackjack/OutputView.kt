package blackjack

object OutputView {

    fun displayDealing(players: List<String>) {
        println("\nDealing two cards to dealer, ${players.joinToString(", ")}.")
    }

    fun displayPlayerHand(player: Player) {
        println("${player.name}'s cards: ${player.displayHand()}")
    }

    fun displayDealerFirstTwoCards(cards: List<Card>) {
        println("Dealer: ${cards[0].display()}, ${cards[1].display()}")
    }

    fun displayInvalidInput() {
        println("Invalid input. Please enter 'y' or 'n'.")
    }
}