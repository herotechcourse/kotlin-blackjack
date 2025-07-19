package blackjack

class InputView {
    private val validator = InputValidator()

    fun readPlayersName(): List<String> {
        while (true) {
            try {
                val players = readln().trim().split(",")
                validator.validateNames(players)
                return players
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}
