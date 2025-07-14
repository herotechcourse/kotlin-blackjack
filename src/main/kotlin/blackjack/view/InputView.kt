package blackjack.view

object InputView {
    private const val NAMES_PROMPT = "Enter the names of the players (comma-separated):"

    fun getPlayersNames(): List<String> {
        println(NAMES_PROMPT)
        val input = readLine() ?: ""
        val playersNames =
            input.trim().split(",").map {
                it.trim().also {
                    require(it.isNotBlank()) { "Wrong name. Player's name should not be blank." }
                }
            }
        require(playersNames.isNotEmpty()) { "Names cannot be empty. You need to provide minimum one name." }
        require(playersNames.toSet().size == playersNames.size) { "Names should be unique." }
        return playersNames
    }

    fun getBettingAmount(playerName: String): Double {
        println(betPrompt(playerName))
        val input = readLine()?.trim() ?: ""
        return try {
            input.toDouble().also {
                require(it > 0.0) { "Betting amount be greater than zero." }
            }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Invalid betting amount: $input. Please enter a valid positive number")
        }
    }

    fun getAnswer(playerName: String): String {
        println(shouldDrawAnotherCardPrompt(playerName))
        val input = readLine()?.trim()?.lowercase() ?: ""
        require(input == "y" || input == "n") {
            "Please answer in order to proceed with the game."
        }
        return input
    }

    private fun betPrompt(playerName: String): String = "Enter $playerName's betting amount:"

    private fun shouldDrawAnotherCardPrompt(playerName: String): String =
        "Would $playerName like to draw another card? (y for yes, n for no)"
}
