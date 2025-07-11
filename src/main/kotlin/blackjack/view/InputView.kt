package blackjack.view

object InputView {
    fun getNamesOfPlayers(): String {
        println(InputPrompt.GET_NAME_OF_PLAYER)
        return readString()
    }

    fun getHitOrStand(name: String): String {
        println(InputPrompt.askHitOrStand(name))
        return readString()
    }

    private fun readString(): String {
        val string = readLine() ?: throw IllegalArgumentException("")
        return string.trim()
    }
}
