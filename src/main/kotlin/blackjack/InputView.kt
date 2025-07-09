package blackjack

object InputView {
    fun askPlayerNames(): List<String> {
        println("Enter the names of the players (comma-separated):")
        val input = readln()
        return input.split(",").map { it.trim() }.filter { it.isNotEmpty() }
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