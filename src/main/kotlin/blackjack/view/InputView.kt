package blackjack.view

import blackjack.model.Bet

object InputView {
    fun askPlayerNames(): List<String> {
        println("Enter the names of the players (comma-separated):")
        val input = readln()
        return input.split(",").map { it.trim() }.filter { it.isNotEmpty() }
    }

    fun getBettingAmount(playerName: String): Bet {
        println("Enter $playerName’s betting amount (multiple of 1000):")
        val input = readLine()?.toIntOrNull()

        return try {
            Bet.from(input ?: 0)
        } catch (e: IllegalArgumentException) {
            println("Invalid amount. Please enter a positive number that is a multiple of 1000.")
            getBettingAmount(playerName)
        }
    }

    fun askPlayerWantsToDraw(playerName: String): Boolean {
        println("Would $playerName like to draw another card? (y for yes, n for no)")
        return when (readln().trim().lowercase()) {
            "y" -> true
            "n" -> false
            else -> {
                println("Invalid input. Please enter 'y' or 'n'.")
                askPlayerWantsToDraw(playerName)
            }
        }
    }
}
