package blackjack.view

object InputView {

    fun readPlayersNames(): List<String> {
        return Ask.retryable(
            Ask::playersName,
            Parser::playerNames,
        )
    }

    internal object Ask {
        fun playersName(): String {
            OutputView.printEnterPlayerName()
            return readlnOrNull() ?: throw IllegalArgumentException(Errors.INVALID_INPUT.message)
        }


        fun forCard(): String {
            while (true) {
                return readlnOrNull() ?: throw IllegalArgumentException(Errors.INVALID_INPUT.message)
            }
        }

        fun <T> retryable(
            ask: () -> String,
            parser: (String) -> T
        ): T {
            while (true) {
                val input = ask()
                try {
                    return parser(input)
                } catch (exception: IllegalArgumentException) {
                    OutputView.printError(exception.message)
                }
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
