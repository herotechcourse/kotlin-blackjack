package blackjack.view

object InputView {
    fun askPlayerNames(): List<String> {
        println("Enter the names of the players (comma-separated):")
        val input = readln()
        return input.split(",").map { it.trim() }.filter { it.isNotEmpty() }
    }

    fun getBettingAmount(playerName: String): Int {
        println("Enter $playerName’s betting amount (multiple of 1000):")
        val input = readLine()?.toIntOrNull()
        return if (input != null && input % 1000 == 0 && input > 0) {
            input
        } else {
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
