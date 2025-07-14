package blackjack.view

import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Participants
import blackjack.model.result.GameResult
import blackjack.model.result.Outcome

object OutputView {
    fun showAllPayersCards(participants: Participants) {
        participants.getPlayers().forEach { showCards(it) }
        println()
    }

    fun showCards(
        participant: Participant,
        extra: String = "",
    ) {
        println("${participant.name}'s cards: " + participant.cards.joinToString() + extra)
    }

    fun showDealerDraw(dealer: Dealer) {
        println("\nDealer draws ${dealer.cardsCount() - 1} more card due to having 16 or less.\n")
    }

    fun askHit(participant: Participant) {
        println("\nWould ${participant.name} like to draw another card? (y for yes, n for no)")
    }

    fun showEnterNames() {
        println(Message.ENTER_PLAYERS_NAMES)
    }

    fun showError(msg: String?) {
        println("Error: $msg")
    }

    fun showGameResult(gameResult: GameResult) {
        val playersResults = gameResult.playersResults
        showCards(gameResult.dealer, showPoints(gameResult.dealer))
        playersResults.forEach { (player, _) ->
            showCards(player, showPoints(player))
        }

        println("\n${Message.FINAL_RESULTS_TITLE}")

        val dealerWin = playersResults.filter { it.value == Outcome.LOSE }.size
        val dealerLose = playersResults.filter { it.value == Outcome.WIN }.size
        val dealerDraw = playersResults.size - dealerLose - dealerWin
        val showDraw = if (dealerDraw > 0) ", $dealerDraw Draw" else ""

        println("Dealer: $dealerWin Win $dealerLose Lose$showDraw")

        playersResults.forEach { (player, outcome) ->
            println("${player.name}: ${outcome.name}")
        }
    }

    private fun showPoints(participant: Participant): String {
        return " - Total: ${participant.score}"
    }

    object Message {
        const val ENTER_PLAYERS_NAMES = "Enter the names of the players (comma-separated):"
        const val GENERATE_NEW_CARD = "Card deck is empty..."
        const val FINAL_RESULTS_TITLE = "## Final Results"
        const val INVALID_INPUT = "Invalid Input"
        const val EMPTY_BUT_TRY_TO_DRAW = "Logic error: can not draw empty or less then tried to draw."
    }
}
