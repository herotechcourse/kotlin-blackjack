package blackjack.model

class Player(name: String) : Participant(name, PlayerResultTracker()) {
    override fun resultSummary(): String {
        return "$name: $resultTracker"
    }
}
