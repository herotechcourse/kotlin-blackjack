package blackjack.view

import blackjack.validator.Validator

object InputView {
    fun readPlayersNames(): List<String> {
        return Ask.retryable(
            Ask::playersName,
            Parser::playerNames,
        )
    }

    fun readUserAnswer(): Boolean {
        return Ask.retryable(
            Ask::nextCard,
            Parser::cardChoice,
        )
    }

    fun readPlayerBet(name: String): Int {
        return Ask.retryable(
            { Ask.bet(name) },
            Parser::bet,
        )
    }

    fun readPlayersBet(names: List<String>): List<Int> {
        return names.map { name -> readPlayerBet(name) }
    }

    private object Ask {
        fun playersName(): String {
            OutputView.showEnterNames()
            return readlnOrNull() ?: throw IllegalArgumentException(OutputView.Message.INVALID_INPUT)
        }

        fun nextCard(): String {
            while (true) {
                return readlnOrNull() ?: throw IllegalArgumentException(OutputView.Message.INVALID_INPUT)
            }
        }

        fun bet(name: String): String {
            OutputView.showHowMuchBet(name)
            return readlnOrNull() ?: throw IllegalArgumentException(OutputView.Message.INVALID_INPUT)
        }

        fun <T> retryable(
            ask: () -> String,
            parser: (String) -> T,
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
            val names =
                input.split(",")
                    .map { it.trim() }
                    .filterNot { it.isBlank() }

            names.forEach { Validator.name(it) }
            return names
        }

        fun bet(input: String): Int {
            val amount = input.toInt()
            Validator.amount(amount)
            return amount
        }

        fun cardChoice(input: String): Boolean {
            return when (input) {
                "y" -> true
                "n" -> false
                else -> throw IllegalArgumentException("${OutputView.Message.INVALID_INPUT}, $input: try again.")
            }
        }
    }
}
