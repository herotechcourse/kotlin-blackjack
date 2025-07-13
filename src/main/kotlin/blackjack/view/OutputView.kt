package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Player
import kotlin.collections.joinToString

object OutputView {
    fun displayInitialCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val playerNames = players.joinToString(", ") { it.name }
        val allParticipants = "Dealer, " + playerNames
        println("Dealing two cards to $allParticipants.")
        displayDealerInitialCards(dealer)
        displayPlayersInitialCards(players)
    }

    fun displayPlayersInitialCards(players: List<Player>) {
        players.forEach {
            displayHandCards(it)
        }
    }

    fun displayDealerInitialCards(dealer: Dealer) {
        println("Dealer: ${dealer.cardsInHand[0].rank.face}${dealer.cardsInHand[0].suit.symbol}")
    }

    fun displayHandCards(player: Player) {
        println(player.storePlayerHand())
    }

    fun displayDealerDrawMessage() {
        println("Dealer draws one more card due to having 16 or less.")
    }

    fun displayCardsWithTotalValue(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("${dealer.storePlayerHand()} - Total: ${dealer.calculateTotalValueOfCards()}")
        players.forEach {
            println("${it.storePlayerHand()} - Total: ${it.calculateTotalValueOfCards()}")
        }
    }

    fun displayFinalResults(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("## Final Results")
        println(dealer.getFinalResultForDealer(players))
        players.forEach {
            print("${it.name}: ")
            if (it.isActive) println("Win") else println("Lose")
        }
    }
}
