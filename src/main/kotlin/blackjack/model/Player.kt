package blackjack.model

class Player(name: String) : Participant(name, PlayerResultTracker()) {
    val result: String
        get() = resultTracker.toString()
}
