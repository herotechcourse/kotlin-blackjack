package blackjack.service

import blackjack.view.InputView
import blackjack.view.OutputView

class InputProcessor {
    companion object {
        private const val MAX_ATTEMPTS = 5
        private const val MAX_ATTEMPT_MESSAGE = "Too many attempts"
        const val YES = "y"
        const val NO = "n"
    }

    fun processPlayerNames(): List<String> {
        repeat(MAX_ATTEMPTS) {
            val result = attemptToGetPlayerNames()
            when {
                result.isSuccess -> return result.getOrNull()!!
                else -> OutputView.displayErrorMessages(result.exceptionOrNull()?.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }

    fun processBetAmount(name: String): Int {
        repeat(MAX_ATTEMPTS) {
            val result = attemptToGetBetAmount(name)
            when {
                result.isSuccess -> return result.getOrNull()!!
                else -> OutputView.displayErrorMessages(result.exceptionOrNull()?.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }

    fun processHitOrStay(playerName: String): Boolean {
        repeat(MAX_ATTEMPTS) {
            val result = attemptToGetHitOrStay(playerName)
            when {
                result.isSuccess -> return result.getOrNull()!!
                else -> OutputView.displayErrorMessages(result.exceptionOrNull()?.message)
            }
        }
        throw IllegalArgumentException(MAX_ATTEMPT_MESSAGE)
    }

    private fun attemptToGetPlayerNames(): Result<List<String>> {
        return runCatching {
            val names = InputView.getNamesOfPlayers()
            val validNames =
                names
                    .split(",")
                    .map(String::trim)
                    .filter { it.isNotBlank() }
            when {
                validNames.isEmpty() -> throw IllegalArgumentException("Player names cannot be empty")
                else -> validNames
            }
        }
    }

    private fun attemptToGetBetAmount(name: String): Result<Int> {
        return runCatching {
            InputView.getBetAmount(name)
        }
    }

    private fun attemptToGetHitOrStay(playerName: String): Result<Boolean> {
        return runCatching {
            val answer = InputView.getHitOrStand(playerName)
            convertAnswerToBoolean(answer)
        }
    }

    private fun convertAnswerToBoolean(answer: String): Boolean {
        return when (answer) {
            YES -> true
            NO -> false
            else -> throw IllegalArgumentException("The answer must be y or n.")
        }
    }
}
