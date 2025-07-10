package blackjack.view

object InputView {
    fun getPlayersName(): List<String> {
        println("Enter the names of the players (comma-separated):")
        val input = (readlnOrNull() ?: throw IllegalArgumentException(Errors.INVALID_INPUT.message)).trim()
        return input.split(",").also {
            require(input.isNotEmpty()) { Errors.INVALID_PLAYERS_NAMES.message }
        }
    }

    fun askForCard(): Boolean {
        val input = (readlnOrNull() ?: throw IllegalArgumentException(Errors.INVALID_INPUT.message)).trim()
        return when (input) {
            "y" -> true
            "n" -> false
            else -> askForCard()
        }
    }
}
