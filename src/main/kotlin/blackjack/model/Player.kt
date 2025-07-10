package blackjack.model

class Player(name: String) : Participant(name) {
    override fun getResults(): String {
        return when {
            gameResults.wins > 0 -> "$name: Win"
            gameResults.loses > 0 -> "$name: Lose"
            else -> "$name: Tie"
        }
    }
}
