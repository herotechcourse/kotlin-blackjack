package blackjack.model.participant

import blackjack.model.result.PlayerResultTracker

class Player(name: String) : Participant(name, PlayerResultTracker()) {
    val result: String
        get() = resultTracker.toString()
}
