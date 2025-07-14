package blackjack.view

import blackjack.model.ErrorMessage
import blackjack.model.Player
import blackjack.model.Rules
import blackjack.model.Rules.BET_AMOUNT_UNIT
import blackjack.model.Rules.MIN_BET_AMOUNT
import blackjack.model.validatePlayerName

object InputView {
    private const val YES = "y"
    private const val NO = "n"

    fun readBets(playerNames: List<String>): List<Int> = playerNames.map { name -> readBet(name) }

    private fun readBet(playerName: String): Int {
        repeat(Rules.MAX_TRIES) {
            println("\nEnter ${playerName}'s betting amount:")
            val input = readlnOrNull()
            if (tryValidateBet(input)) return input!!.toInt()
        }
        throw RuntimeException(ErrorMessage.MAX_TRIES.toString())
    }

    private fun tryValidateBet(input: String?): Boolean {
        return try {
            if (input.isNullOrEmpty()) throw IllegalArgumentException(ErrorMessage.EMPTY_INPUT.toString())
            val bettingAmount = input.toIntOrNull() ?: throw IllegalArgumentException(ErrorMessage.NOT_NUMBER.toString())
            if (bettingAmount < MIN_BET_AMOUNT ) throw IllegalArgumentException(ErrorMessage.MIN_BET.toString())
            if (bettingAmount % BET_AMOUNT_UNIT != 0) throw IllegalArgumentException(ErrorMessage.UNIT_BET.toString())
            true
        } catch (e: IllegalArgumentException) {
            println(e.message)
            false
        }
    }

    fun readNames(): List<String> {
        repeat(Rules.MAX_TRIES) {
            println("\nEnter the names of the players (comma-separated):")
            val input = readlnOrNull()
            if (tryValidateNames(input)) return input!!.split(",").map { it.trim() }
        }
        throw RuntimeException(ErrorMessage.MAX_TRIES.toString())
    }

    private fun tryValidateNames(input: String?): Boolean {
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
        throw RuntimeException(ErrorMessage.MAX_TRIES.toString())
    }

    private fun tryValidateDrawInput(input: String): Boolean {
        return try {
            require(input.isNotEmpty()) { ErrorMessage.EMPTY_INPUT.toString() }
            when (input) {
                YES, NO -> true
                else -> throw IllegalArgumentException(ErrorMessage.INVALID_INPUT.toString())
            }
        } catch (e: IllegalArgumentException) {
            println(e.message)
            false
        }
    }
}
