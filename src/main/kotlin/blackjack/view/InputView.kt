package blackjack.view

import kotlin.math.abs

object InputView {
    fun askPlayerNames(): List<String> {
        println("Enter the names of the players (comma-separated):")
        try {
            val names =
                readln()
                    .split(",")
                    .map {
                        it.trim()
                    }
            return names
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Input can not be empty.")
        }
    }

    fun askPlayerBet(name: String): Int {
        println("Enter $name’s betting amount: ")
        try {
            val bet =
                readln()
                    .toInt()
            return abs(bet)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Input must be a valid number.")
        }
    }

    fun askToHit(name: String): Boolean {
        println("Would $name like to draw another card? (y for yes, n for no)")
        val choice = readln()
        return when (choice) {
            "y", "Y" -> true
            "n", "N" -> false
            else -> throw IllegalArgumentException("Input can only contain y for yes, n for no")
        }
    }
}
