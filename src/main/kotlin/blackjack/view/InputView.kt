package blackjack.view

object InputView {
    fun askPlayersName(): String {
        OutputView.printEnterPlayerName()
        return readlnOrNull() ?: throw IllegalArgumentException(Errors.INVALID_INPUT.message)
    }


    fun askForCard(): String {
        while (true) {
            return readlnOrNull() ?: throw IllegalArgumentException(Errors.INVALID_INPUT.message)
        }
    }

    fun <T> retry(
        input : () -> String,
        result: (String) -> T
    ): T {
        while (true) {
            val input = input()
            try {
                return result(input)
            } catch (exception: IllegalArgumentException) {
                OutputView.printError(exception.message)
            }
        }
    }

    internal object Parser {
        fun playerNames(input: String): List<String> {
            return input.split(",")
                .map { it.trim() }
                .filterNot { it.isBlank() }
                .ifEmpty { throw IllegalArgumentException(Errors.INVALID_INPUT.message + ": $input") }
        }

        fun cardChoice(input: String): Boolean {
            return when (input) {
                "y" -> true
                "n" -> false
                else -> throw IllegalArgumentException("${Errors.INVALID_INPUT.message}, try again.")
            }
        }
    }
}
