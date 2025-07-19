package blackjack.validator

import blackjack.view.OutputView

object Validator {
    private const val ANTE = 1000
    private const val MAX_NAME_LENGTH = 10

    fun name(name: String) {
        require(isValidNameLength(name)) { "${OutputView.Message.INVALID_INPUT}, $name: too long, try again." }
        require(isAlphanumeric(name)) { "${OutputView.Message.INVALID_INPUT}, $name: should alphanumeric." }
    }

    fun amount(amount: Int) {
        require(meetsAnte(amount)) { "${OutputView.Message.INVALID_INPUT}, $amount: should be bigger than $ANTE, try again." }
        require(divisibleByAnte(amount)) { "${OutputView.Message.INVALID_INPUT}, $amount: try again." }
    }

    fun createPlayers(
        names: List<String>,
        amounts: List<Int>,
    ) {
        require(names.isNotEmpty() && amounts.isNotEmpty()) { "Names and amounts lists cannot be empty" }
        require(names.size == amounts.size) { "Names count (${names.size}) must match amounts count (${amounts.size})" }
    }

    private fun isValidNameLength(name: String): Boolean = name.isNotEmpty() && name.length in 1..MAX_NAME_LENGTH

    private fun isAlphanumeric(name: String): Boolean = name.all { it.isLetterOrDigit() }

    private fun divisibleByAnte(amount: Int): Boolean = amount % ANTE == 0

    private fun meetsAnte(amount: Int): Boolean = amount >= ANTE
}
