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

    fun askPlayerIfShouldHit(participant: Participant): Boolean {
        println()
        println("Would ${participant.name} like to draw another card? (y for yes, n for no)")
        val wantHit = readln().trim()
        return wantHit == "y"
    }
}
