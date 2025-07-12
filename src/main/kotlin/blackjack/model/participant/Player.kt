package blackjack.model.participant

import blackjack.view.Errors

class Player(name: String) : Participant(name) {
    init {
        require(name.all { it.isLetter() }) { Errors.INVALID_PLAYERS_NAMES }
    }
}
