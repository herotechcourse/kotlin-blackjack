package blackjack

class OutputView {
    fun printNameQuestion () {
        println(ASK_NAMES)
    }

    fun printPlayersIntroToTheirCards (players: List<Player>) {
        val playersNames = players.map { it.name }
        println()
        println(FIRST_TURN_CARDS.format(playersNames.joinToString(",")))
    }

    fun printFirstTurnCards (participant: Participant) {
        val cards = participant.hand.toString()
        println(DISPLAY_HANDS.format(participant.name, cards))
    }

    companion object Messages {
        const val ASK_NAMES = "Enter the names of the players (comma-separated):"
        const val FIRST_TURN_CARDS = "Dealing two cards to dealer, %s."
        const val DISPLAY_HANDS = "%s`s cards: %s"
    }
}