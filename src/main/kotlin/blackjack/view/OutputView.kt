package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Participant
import blackjack.model.Player

object OutputView {

    fun showHandCards(participant: Participant, isFirstRound: Boolean) : String {
         return when {
             participant is Dealer && isFirstRound-> "${participant.name}: ${participant.cardsInHand[0].showCardFace()}"

             else -> "${participant.name}´s cards: ${participant.cardsInHand.joinToString(", ") { card -> (card.showCardFace()) }}"
        }
    }

    fun displayInitialCards(
        dealer: Dealer,
        players: List<Player>,
        isFirstRound: Boolean = true
    ) {
        val playerNames = players.joinToString(", ") { it.name }
        println("\nDealing two cards to ${dealer.name}, $playerNames.")
        println(showHandCards(dealer, isFirstRound))
        players.forEach { player ->
                println(showHandCards(player, isFirstRound))
        }
        println()
    }

    fun displayDealerDrawMessage(dealer: Dealer) {
        println("\n${dealer.name} draws one more card due to having 16 or less.\n")
    }

    fun displayCardsWithTotalValue(
        participant: Participant, isFirstRound: Boolean = false
    ) {
        println("${showHandCards(participant, isFirstRound)} - Total: ${participant.calculateTotalValueOfCards()}")
    }

    fun getFinalResultForDealer(players: List<Player>): String {
        val lose = players.count { !it.isBusted }
        val win = players.count { !it.isBusted }
        return "$win Win $lose Lose"
    }

    fun displayFinalResults(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("\n## Final Results")
        println("${dealer.name}: ${getFinalResultForDealer(players)}")
        players.forEach {
            print("${it.name}: ")
            if (!it.isBusted) println("Win") else println("Lose")
        }
    }
}
