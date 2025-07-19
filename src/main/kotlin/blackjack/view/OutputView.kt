package blackjack.view

import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import blackjack.model.result.GameResult

object OutputView {
    fun showFirstRound(participants: Participants) {
        showCards(participants.dealer)
        showAllPayersCards(participants.players)
    }

    fun showDealerDraw(dealer: Dealer) {
        println("\nDealer draws ${dealer.cardsCount() - 1} more card due to having 16 or less.\n")
    }

    fun showHowMuchBet(name: String) = println("Enter $name’s betting amount: ")

    fun askHit(participant: Participant) {
        println("\nWould ${participant.name} like to draw another card? (y for yes, n for no)")
    }

    fun showEnterNames() = println(Message.ENTER_PLAYERS_NAMES)

    fun showError(msg: String?) = println("Error: $msg")

    fun showGameResult(gameResult: GameResult) {
        showCards(gameResult.dealer, showPoints(gameResult.dealer))
        showAllPayersCardsAndPoints(gameResult.participants.players)
        showGameSummary(gameResult)
    }

    internal fun showAllPayersCards(players: List<Player>) {
        players.forEach { player ->
            showCards(player)
        }
        println()
    }

    private fun showAllPayersCardsAndPoints(players: List<Player>) {
        players.forEach { player ->
            showCards(player, showPoints(player))
        }
        println()
    }

    internal fun showCards(
        participant: Participant,
        extra: String = "",
    ) {
        println("${participant.name}'s cards: " + participant.cards.joinToString() + extra)
    }

    internal fun showGameSummary(gameResult: GameResult) {
        println("\n${Message.FINAL_RESULTS_TITLE}")
        println("Dealer: ${gameResult.dealerEarning.amount}")
        gameResult.playerResults.forEach {
            println("${it.player.name}: ${it.finalAmount}")
        }
    }

    private fun showPoints(participant: Participant): String {
        return " - Total: ${participant.score}"
    }

    object Message {
        const val ENTER_PLAYERS_NAMES = "Enter the names of the players (comma-separated):"
        const val FINAL_RESULTS_TITLE = "## Final Results"
        const val INVALID_INPUT = "Invalid Input"
    }
}
