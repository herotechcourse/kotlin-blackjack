package blackjack.view

object InputView {
    fun getNamesOfPlayers(): String {
        println(InputPrompt.GET_NAME_OF_PLAYER)
        return readString()
    }

    private fun readString(): String {
        val string = readlnOrNull() ?: throw IllegalArgumentException(ErrorPrompt.INPUT_EMPTY)
        return string.trim()
    }

    fun getHitOrStand(name: String): String {
        println(InputPrompt.askHitOrStand(name))
        return readString()
    }

    fun getBetAmount(name: String): Int {
        println(InputPrompt.askBetAmount(name))
        return getPositiveNumber()
    }

    private fun getPositiveNumber(): Int {
        val number = getInt()
        require(number > 0) { ErrorPrompt.INPUT_INVALID_INTEGER }
        return number
    }

    private fun getInt(): Int {
        val int =
            readString().toIntOrNull()
                ?: throw IllegalArgumentException(ErrorPrompt.INPUT_INVALID_INTEGER)
        return int
    }
}
