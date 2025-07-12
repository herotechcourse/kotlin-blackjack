package blackjack.view

object InputView {
    fun getPlayersName(): List<String> {
        OutputView.printEnterPlayerName()
        val input = (readlnOrNull() ?: throw IllegalArgumentException(Errors.INVALID_INPUT.message)).trim()
        return input.split(",").map {
            it.trim()
        }.filterNot { it.isBlank() }
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
