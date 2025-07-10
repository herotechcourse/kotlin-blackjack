package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Participant
import blackjack.model.Player
import kotlin.collections.joinToString

class OutputView {
    fun displayInitialCards(participants: List<Participant>) {
        val names = participants.joinToString(", ") { it.name }
        println("Dealing two cards to $names.")
        participants.forEach {
            if (it is Dealer) {
                println("Dealer: ${it.cardsInHand[0].rank.face}${it.cardsInHand[0].suit.symbol}")
            }
            displayHandCards(it)
        }
    }

    fun displayHandCards(participant: Participant) {
        println(participant.storePlayerHand())
    }

    fun displayDealerDrawMessage(mustDraw: Boolean) {
        println("Dealer draws one more card due to having 16 or less.")
    }

    fun displayCardsWithTotalValue(participants: List<Participant>) {
        participants.forEach {
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
