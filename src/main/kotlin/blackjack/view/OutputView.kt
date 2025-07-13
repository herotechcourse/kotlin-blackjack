package blackjack.view

import blackjack.model.Dealer
import blackjack.model.DealerResult
import blackjack.model.Participant
import blackjack.model.PlayerResult
import blackjack.model.Players

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

    fun displayParticipantStatus(participant: Participant)  {
        if (participant is Dealer) {
            println()
        }
        println("${participant.name}'s cards: ${participant.cardsToString()} – Total: ${participant.handCards.total}")
    }

    fun formatDealer(result: DealerResult): String =
        "Dealer: ${result.wins} wins, ${result.losses} losses, ${result.draws} draws"

    fun formatPlayer(result: PlayerResult): String =
        when {
            result.win  -> "${result.name}: win"
            result.draw -> "${result.name}: draw"
            else        -> "${result.name}: loss"
        }

    // TODO: refactor Pair to value class?
    fun displayResults(results: Pair<DealerResult, List<PlayerResult>>) {
        println(
            """
            |
            |## Final Results:
            |${formatDealer(results.component1())}
            |${results.component2().joinToString("\n") { formatPlayer(it) }}
            """.trimMargin(),
        )
    }
}
