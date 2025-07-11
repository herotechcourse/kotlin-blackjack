package blackjack.view

import blackjack.model.Dealer
import blackjack.model.DealerResult
import blackjack.model.Participant
import blackjack.model.PlayerResult
import blackjack.model.Players
import blackjack.model.Results

object OutputView {
    fun displayPlayerNames(players: Players) {
        println(players.players.joinToString())
    }

    fun displayInitialCardsMessage(players: Players) {
        println("\nDealing two cards to dealer, ${players.players.joinToString()}.")
    }

    fun displayAllCardsMessage(participant: Participant) {
        println("${participant.name}'s cards: ${participant.cardsToString()}")
    }

    fun displayFirstCardMessage(participant: Participant) {
        println("${participant.name}: ${participant.firstCardToString()}")
    }

    fun displayDealerDrawMessage() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayParticipantStatus(participant: Participant){
        if (participant is Dealer)
            println()
        print("${participant.name}'s cards: ${participant.cardsToString()}")
        println(" – Total: ${participant.handCards.total}")
    }

    fun dealerResultString(result: DealerResult): String {
        return "Dealer: ${result.wins} wins, ${result.losses} losses, ${result.draws} draws"
    }

    fun playerResultToString(result: PlayerResult): String {
        if (result.win) return "${result.name}: win"
        if (result.draw) return "${result.name}: draw"
        return "${result.name}: loss"
    }

    fun displayResults(results: Results) {
        println("\n## Final Results:")
        println(dealerResultString(results.dealerResult))
        results.component2().forEach { println(playerResultToString(it)) }
    }
}
