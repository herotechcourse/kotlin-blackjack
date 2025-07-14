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
}
