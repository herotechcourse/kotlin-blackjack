package blackjack.view

import blackjack.model.ErrorMessage
import blackjack.model.Player
import blackjack.model.Rules
import blackjack.model.validatePlayerName

object InputView {

    private const val YES = "y"
    private const val NO = "n"

    fun readNames(): List<String> {
        repeat(Rules.MAX_TRIES) {
            println("\nEnter the names of the players (comma-separated):")
            val input = readlnOrNull()
            if (tryValidateNames(input)) return input!!.split(",").map { it.trim() }
        }
        throw RuntimeException(ErrorMessage.MAX_TRIES.message)
    }

    fun tryValidateNames(input: String?): Boolean {
        return try {
            if (input.isNullOrEmpty()) throw IllegalArgumentException(ErrorMessage.EMPTY_INPUT.toString())
            val names = input.split(",").map { it.trim() }
            names.forEach { validatePlayerName(it) }
            true
        } catch (e: IllegalArgumentException) {
            println(e.message)
            false
        }
    }

    fun promptForDraw(player: Player): Boolean {
        repeat(Rules.MAX_TRIES) {
            println("\nWould ${player.name} like to draw another card? (y for yes, n for no)")
            val input = readln().trim()
            if (tryValidateDrawInput(input)) {
                return input == YES
            }
        }
        throw RuntimeException(ErrorMessage.MAX_TRIES.message)
    }

    private fun tryValidateDrawInput(input: String): Boolean {
        return try {
            require(input.isNotEmpty()) { ErrorMessage.EMPTY_INPUT.message }
            when (input) {
                YES, NO -> true
                else -> throw IllegalArgumentException(ErrorMessage.INVALID_INPUT.message)
            }
        } catch (e: IllegalArgumentException) {
            println(e.message)
            false
        }
    }
}
