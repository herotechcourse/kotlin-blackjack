package blackjack.view

object InputView {
    fun getPlayersName(): List<String> {
        println("Enter the names of the players (comma-separated):")
        val input = (readlnOrNull() ?: throw IllegalArgumentException(Errors.INVALID_INPUT.message)).trim()
        return input.split(",").map {
            it.trim()
        }.filterNot { it.isBlank() }
    }
}
