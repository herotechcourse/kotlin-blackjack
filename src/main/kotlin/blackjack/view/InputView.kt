package blackjack.view

import blackjack.model.Player

object InputView {
    fun getPlayersNames(): List<String> {
        println("Enter the names of the players (coma-separated):")
        val input = readlnOrNull() ?: ""
        val playersNames =
            input.trim().split(",")
                .map { name ->
                    name.trim().also { require(it.isNotBlank()) { "Wrong name. Player's name should not be blank." } }
                }
        require(playersNames.isNotEmpty()) {
            "Names cannot be empty. You need to provide minimum one name."
        }
        require(playersNames.toSet().size == playersNames.size) {
            "Names should be unique."
        }
        return playersNames
    }

    fun getAnswer(player: Player): Boolean {
        println("Would ${player.name} like to draw another card? (y for yes, n for no)")
        val input = readlnOrNull()?.trim()?.lowercase() ?: ""
        return when (input) {
            "y" -> true
            "n" -> false
            else -> {
                println("Invalid input. Please type 'y' or 'n'.")
                getAnswer(player)
            }
        }
    }

    fun getBettingAmount(player: Player): Int {
        println("Enter ${player.name}'s betting amount:")
        val inputString = readlnOrNull()?.trim() ?: ""
        val amount = inputString.toIntOrNull()
        require(amount != null && amount > 0) {
            "Input cannot be empty."
        }
        return amount
    }
}
