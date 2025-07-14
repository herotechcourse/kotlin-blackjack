package blackjack.view

import blackjack.model.card.Card
import blackjack.model.participant.Participants
import blackjack.model.participant.Player

object OutputView {
    private const val DEALER_DREW_MESSAGE = "Dealer draws one more card due to having 16 or less."
    private const val DEALER_STAND_MESSAGE = "Dealer stands directly."
    private const val RESULTS_HEAD = "## Final Results"

    fun printParticipantsHands(participants: Participants) {
        printEmptyLine()
        println("Dealing two cards to ${participants.dealer.name}, ${getPlayersNames(participants.players)}.")

        println("${participants.dealer}'s cards: ${getColorizedCards(participants.dealer.hand)}")
        participants.players.forEach { println("$it's cards: ${getColorizedCards(it.hand)}") }

        printEmptyLine()
    }

    fun printPlayerInfo(player: Player) {
        println("$player's cards: ${getColorizedCards(player.hand)}")
    }

    fun printDealersDrawMessage() {
        println(DEALER_DREW_MESSAGE)
        printEmptyLine()
    }

    fun printDealersStandMessage() {
        printEmptyLine()
        println(DEALER_STAND_MESSAGE)
        printEmptyLine()
    }

    fun printFinalHands(participants: Participants) {
        print("${participants.dealer}'s cards: ${getColorizedCards(participants.dealer.hand)} ")
        println("- Total: ${participants.dealerScore}")
        participants.players.forEach {
            println("$it's cards: ${getColorizedCards(it.hand)} - Total: ${it.getScore()}")
        }
    }

    fun printResults(participants: Participants) {
        printEmptyLine()
        println(RESULTS_HEAD)
        println("${participants.dealerName}: ${participants.dealerResult}")
        participants.players.forEach { println("${it.name}: ${it.result}") }
    }

    fun printErrorMessage(message: String) {
        println("\n${AnsiColor.RED}[ERROR]: $message\n${AnsiColor.RESET}")
    }

    fun printEmptyLine() {
        println()
    }

    fun getColorizedCards(cards: List<Card>): String {
        return cards.joinToString(", ") { getColorizedCard(it) }
    }

    fun getColorizedCard(card: Card): String {
        return when (card.suit) {
            Card.Suit.DIAMONDS, Card.Suit.HEARTS -> "${AnsiColor.RED_ON_WHITE}${card}${AnsiColor.RESET}"
            else -> "${AnsiColor.BLACK_ON_WHITE}${card}${AnsiColor.RESET}"
        }
    }

    private fun getPlayersNames(players: List<Player>): String {
        return players.joinToString(", ") { it.name }
    }
}
