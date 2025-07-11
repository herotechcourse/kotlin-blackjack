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

    private fun readString(): String {
        val string = readLine() ?: throw IllegalArgumentException("")
        return string.trim()
    }

    private fun askHitOrStand(name: String): String = "Would $name like to draw another card? (y for yes, n for no)"

    private const val GET_NAME_OF_PLAYER = "Enter the names of the players (comma-separated):"
}
