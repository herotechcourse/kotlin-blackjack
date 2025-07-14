package blackjack.view

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
        println()
        println("Enter $name’s betting amount:")
        return try {
            readLine()?.toIntOrNull()?.takeIf { it > 0 } ?: askPlayerBet(name)
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("Betting amount must be greater than 0")
        }
    }

    fun askToDrawCard(name: String): Boolean {
        println("Would $name like to draw another card? (y for yes, n for no)")
        val choice = readln()
        return when (choice) {
            "y", "Y" -> true
            "n", "N" -> false
            else -> {
                println("Invalid input. Please enter 'y' or 'n'.")
                askToDrawCard(name)
            }
        }
    }
}
