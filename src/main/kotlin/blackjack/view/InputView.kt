package blackjack.view

import blackjack.model.Player

object InputView {
    fun getPlayersName(): List<String> {
        println("Enter the names of the players (comma-separated):")
        val input = (readlnOrNull() ?: throw IllegalArgumentException(Errors.INVALID_INPUT.message)).trim()
        return input.split(",").map {
            it.trim()
        }.filterNot { it.isBlank() }
    }

    fun askForCard(player: Player): Boolean {
        println("Would ${player.name} like to draw another card? (y for yes, n for no)")
        val input = (readlnOrNull() ?: throw IllegalArgumentException(Errors.INVALID_INPUT.message)).trim()
        return when (input) {
            "y" -> true
            "n" -> false
            else -> askForCard(player)
        }
    }
}
