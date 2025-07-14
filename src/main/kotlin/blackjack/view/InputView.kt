package blackjack.view

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

    fun getAnswer(playerName: String): String {
        println("Would $playerName like to draw another card? (y for yes, n for no)")
        val input = readlnOrNull()?.trim()?.lowercase() ?: ""
        require(input == "y" || input == "n") {
            "Please answer in order to proceed with the game."
        }
        return input
    }
}
