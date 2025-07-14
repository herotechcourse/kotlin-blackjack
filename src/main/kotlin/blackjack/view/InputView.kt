package blackjack.view

object InputView {
    fun getNamesOfPlayers(): String {
        println(GET_NAME_OF_PLAYER)
        return readString()
    }

    fun getHitOrStand(name: String): String {
        println(askHitOrStand(name))
        return readString()
    }

    fun getBetAmount(name: String): Int {
        println(askBetAmount(name))
        return readln().trim().toIntOrNull() ?: throw IllegalArgumentException(BET_ERROR_MESSAGE)
    }

    private fun readString(): String {
        val string = readLine() ?: throw IllegalArgumentException(STRING_ERROR_MESSAGE)
        return string.trim()
    }

    private fun askHitOrStand(name: String): String = "Would $name like to draw another card? (y for yes, n for no)"

    private fun askBetAmount(name: String): String = "Enter $name’s betting amount:"

    private const val GET_NAME_OF_PLAYER = "Enter the names of the players (comma-separated):"
    private const val BET_ERROR_MESSAGE = "PLease enter valid amount"
    private const val STRING_ERROR_MESSAGE = "Please enter a valid input"
}
