package blackjack.view

object InputView {

    fun readPlayersNames(): List<String> {
        return Ask.retryable(
            Ask::playersName,
            Parser::playerNames,
        )
    }

    fun readUserAnswer() : Boolean {
        return Ask.retryable(
            Ask::forCard,
            Parser::cardChoice,
        )
    }

    internal object Ask {
        fun playersName(): String {
            OutputView.showEnterNames()
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
                    OutputView.showError(exception.message)
                }
            }
        }
    }

    internal object Parser {
        fun playerNames(input: String): List<String> {
            val names = input.split(",")
                .map { it.trim() }
                .filterNot { it.isBlank() }

            if (names.isEmpty() || names.any { !it.all { char -> char.isLetterOrDigit() } }) {
                throw IllegalArgumentException(Errors.INVALID_INPUT.message + ": $input")
            }

            return names
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
