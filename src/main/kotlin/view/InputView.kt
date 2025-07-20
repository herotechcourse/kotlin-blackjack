package view

import model.BasePlayer

class InputView {
    fun requestPlayerNames(): List<String> =
        retry("Enter the names of the players (comma-separated):") {
            parsePlayerNames(readln())
        }

    fun requestPlayerDecision(player: BasePlayer): Boolean =
        retry("Would ${player.name} like to draw another card? (y for yes, n for no)") {
            parsePlayerDecision(readln())
        }

    fun requestPlayersBet(player: BasePlayer): Int =
        retry("Enter ${player.name}’s betting amount:") {
            parsePlayerBet(readln())
        }

    private fun parsePlayerNames(input: String): List<String> {
        require(input.isNotEmpty() && input.isNotBlank()) {
            "Input cannot be empty"
        }
        return input.split(",").map { it.trim() }
    }

    private fun parsePlayerDecision(input: String): Boolean {
        require(input.isNotEmpty() && input.isNotBlank()) { "Decision cannot be empty" }
        require(input == "y" || input == "n") { "Decision has to be 'y' or 'n'" }
        return input.trim() == "y"
    }

    private fun parsePlayerBet(input: String): Int {
        require(input.isNotEmpty() && input.isNotBlank()) { "Decision cannot be empty" }
        return input.toInt()
    }

    private fun <T> retry(
        prompt: String,
        block: () -> T,
    ): T {
        while (true) {
            try {
                println(prompt)
                return block()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}
